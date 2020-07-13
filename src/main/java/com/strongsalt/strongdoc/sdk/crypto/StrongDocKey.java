/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.strongsalt.strongdoc.sdk.crypto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class StrongDocKey implements KeyBase {

	public enum KeyVersion implements VersionInterface {
		ONE(1);

		private Version version;
		static {
			VersionInterface.setClassVersions(KeyVersion.class.getName(), KeyVersion.values());
		}

		private KeyVersion() {}
		private KeyVersion(final int version) {
			this.version = new Version(version);
		}

		@Override
		public Version getVersion() {
			return version;
		}

		public static KeyVersion Deserialize(final byte[] data) throws StrongDocKeyException {
			return VersionInterface.Deserialize(KeyVersion.class, data);
		}
	}

	public static enum KeyType {
		RSA4K(false, false, Rsa4kKey.class),
		RSA2K(false, false, Rsa2kKey.class),
		EC_X25519(false, false, EcX25519Key.class),
		AES_GCM(true, false, AesGcmKey.class),
		XCHACHA20(true, false, XChaCha20Key.class),
		XCHACHA20_STREAM(true, true, XChaCha20StreamKey.class);

		private KeyType() {}
		private KeyType(final boolean symmetric, final boolean midstream, final Class<? extends KeySerialization> cls) {
			setSymmetric(symmetric);
			setMidstream(midstream);
			setKeyTypeClass(cls);
		}

		private boolean symmetric;
		private boolean midstream;
		private Class<? extends KeySerialization> cls;

		public static String GenerateKeyFunction = "GenerateKey";
		public static String DeserializeFunction = "Deserialize";
		private static Map<String, KeyType> map = new HashMap<String, KeyType>();

		static {
			for (KeyType type : KeyType.values()) {
				map.put(type.toString().toUpperCase(), type);
				map.put(type.toString().toLowerCase(), type);

				Class<? extends KeySerialization> cls = type.getKeyTypeClass();
				if (cls == null) {
					continue;
				}

				try {
					cls.getMethod(DeserializeFunction, byte[].class);
				} catch (NoSuchMethodException | SecurityException e) {
					throw new RuntimeException("Class " + cls.getName() +
							" is missing the " + DeserializeFunction + "() function", e);
				}

				try {
					type.getKeyTypeClass().getMethod(GenerateKeyFunction);
				} catch (NoSuchMethodException | SecurityException e) {
					throw new RuntimeException("Class " + type.getKeyTypeClass().getName() +
							" is missing the " + GenerateKeyFunction + "() function", e);
				}
			}
		}

		public static KeyType Deserialize(final byte[] data) throws StrongDocKeyException {
			final String keytype = new String(data);
			if (!map.containsKey(keytype)) {
				throw new StrongDocKeyException("StrongDocKey type " + keytype + " is not supported");
			}
			return map.get(keytype);
		}

		public boolean isSymmetric() {
			return symmetric;
		}

		private void setSymmetric(boolean symmetric) {
			this.symmetric = symmetric;
		}

		public Class<? extends KeySerialization> getKeyTypeClass() {
			return this.cls;
		}

		private void setKeyTypeClass(Class<? extends KeySerialization> cls) {
			this.cls = cls;
		}

		public boolean isMidstream() {
			return midstream;
		}

		private void setMidstream(boolean midstream) {
			this.midstream = midstream;
		}
	}

	private static KeyVersion curVersion = KeyVersion.ONE;

	private KeyVersion version;
	private KeyType type;
	private KeyBase key;

	private StrongDocKey() {}
	private StrongDocKey(final KeyVersion version, final KeyType type) {
		super();
		this.setVersion(version);
		this.setType(type);
	}

	public static StrongDocKey GenerateKey(KeyType type) throws StrongDocKeyException {
		StrongDocKey key = new StrongDocKey(curVersion, type);

		try {
			Class<? extends KeySerialization> cls = type.getKeyTypeClass();
			Method m = cls.getMethod(KeyType.GenerateKeyFunction);
			Object k = m.invoke(null);
			key.setKey((KeyBase) k);
			return key;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new StrongDocKeyException("Can not generate key of type " + type.name(), e);
		}
	}

	// The serialization/deserialization format is as follows:
	//
	// Version 1:
	//  ---------------------------------------------------------
	// | version(4 bytes) | keyTypeLen(2 bytes) | keyType | data |
	//  ---------------------------------------------------------
	//
	// For format of the "data" portion will depend on the key type. Each key type
	// will be handled by a separate class, and the format will be defined in the
	// specified class.
	//
	public static StrongDocKey Deserialize(final byte[] data) throws StrongDocKeyException {
		StrongDocKey key = new StrongDocKey();
		key.deserialize(data);
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		ByteBuffer buf = ByteBuffer.wrap(data);

		byte[] ver = new byte[curVersion.getSeriaizedSize()];
		buf.get(ver);
		KeyVersion version = KeyVersion.Deserialize(ver);
		setVersion(version);

		switch(version) {
		case ONE:
			byte[] type = new byte[buf.getShort()];
			buf.get(type);
			KeyType keyType = KeyType.Deserialize(type);
			setType(keyType);

			byte[] remaining = new byte[buf.remaining()];
			buf.get(remaining);
			try {
				Class<? extends KeySerialization> cls = keyType.getKeyTypeClass();
				Method m = cls.getMethod(KeyType.DeserializeFunction, byte[].class);
				Object k = m.invoke(null, remaining);
				setKey((KeyBase) k);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				throw new StrongDocKeyException("Can not deserialize key type " + keyType.name(), e);
			}
			break;
		default:
			throw new StrongDocKeyException("Unknown key version " + version.getVersion().getVersion());
		}

		return (T) this;
	}

	@Override
	public byte[] serialize() {
		byte[] serialKey = getKey().serialize();
		String typeName = getType().name();
		ByteBuffer buf = ByteBuffer.allocate(curVersion.getSeriaizedSize() +
				2 + typeName.length() + serialKey.length);
		buf.put(curVersion.serialize());
		buf.putShort((short) typeName.length());
		buf.put(typeName.getBytes());
		buf.put(serialKey);

		return buf.array();
	}

	@Override
	public byte[] encrypt(byte[] plaintext) throws StrongDocKeyException {
		return getKey().encrypt(plaintext);
	}

	public byte[] encrypt(byte[] plaintext, long offset) throws StrongDocKeyException {
		// TODO
		if (!getType().isMidstream()) {
			throw new StrongDocKeyException("Key type " + getType().name() + " is not capable of mid stream encrypt/decrypt");
		}

		return null;
	}

	@Override
	public byte[] decrypt(byte[] ciphertext) throws StrongDocKeyException {
		return getKey().decrypt(ciphertext);
	}

	public byte[] decrypt(byte[] ciphertext, long offset) throws StrongDocKeyException {
		// TODO
		if (!getType().isMidstream()) {
			throw new StrongDocKeyException("Key type " + getType().name() + " is not capable of mid stream encrypt/decrypt");
		}

		return null;
	}

	public KeyVersion getVersion() {
		return version;
	}

	private void setVersion(KeyVersion version) {
		this.version = version;
	}

	public KeyType getType() {
		return type;
	}

	private void setType(KeyType type) {
		this.type = type;
	}

	public boolean isSymmetricKey() {
		return getType().isSymmetric();
	}

	public KeyBase getKey() {
		return key;
	}

	private void setKey(KeyBase key) {
		this.key = key;
	}

}

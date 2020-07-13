package com.strongsalt.strongdoc.sdk.crypto;

import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.util.HashMap;
import java.util.Map;

import com.strongsalt.strongdoc.sdk.crypto.StrongDocKey.KeyType;

public class RsaKey implements KeyAsymmetric {
	private RsaKeyPub pubKey;
	private RsaKeyPri priKey;
	private RsaKeyVer curVersion = RsaKeyVer.ONE;


	public static enum RsaKeySize {
		RSA4K(4096, 382), RSA2K(2048, 126);

		private static Map<Integer, RsaKeySize> keySizeMap = new HashMap<>();
		static {
			for (RsaKeySize size : RsaKeySize.values()) {
				keySizeMap.put(size.getKeySize(), size);
			}
		}

		private int keySize;
		private int dataSize;

		private RsaKeySize() {}
		private RsaKeySize(final int keySize, final int dataSize) {
			setKeySize(keySize);
			setDataSize(dataSize);
		}

		public int getKeySize() {
			return keySize;
		}

		private void setKeySize(int size) {
			this.keySize = size;
		}

		public int getDataSize() {
			return dataSize;
		}

		private void setDataSize(int dataSize) {
			this.dataSize = dataSize;
		}

		public static RsaKeySize parseKeySize(int keySize) {
			return keySizeMap.get(keySize);
		}
	}

	public enum RsaKeyVer implements VersionInterface {
		ONE(1);

		private Version version;
		static {
			VersionInterface.setClassVersions(RsaKeyVer.class.getName(), RsaKeyVer.values());
		}

		private RsaKeyVer() {}
		private RsaKeyVer(final int version) {
			this.version = new Version(version);
		}

		public static RsaKeyVer Deserialize(final byte[] data) throws StrongDocKeyException {
			return VersionInterface.Deserialize(RsaKeyVer.class, data);
		}

		@Override
		public Version getVersion() {
			return version;
		}
	}

	protected RsaKey() {}
	protected RsaKey(RsaKeyPub pubKey, RsaKeyPri priKey){
		this.setPrivateKey(priKey);
		this.setPublicKey(pubKey);
	}

	public static KeyPair GenerateKey(final RsaKeySize size) throws StrongDocKeyException {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(size.getKeySize());
			KeyPair keyPair = kpg.genKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException e) {
			throw new StrongDocKeyException("Can not generate RSA key", e);
		}
	}

	@Override
	public byte[] encrypt(byte[] plaintext) throws StrongDocKeyException {
		return this.getPublicKey().encrypt(plaintext);
	}

	@Override
	public byte[] decrypt(byte[] ciphertext) throws StrongDocKeyException {
		return this.getPrivateKey().decrypt(ciphertext);
	}

	public static RsaKey Deserialize(final byte[] data) throws StrongDocKeyException {
		RsaKey key = new RsaKey();
		return key.deserialize(data);
	}

	//
	// The serialization/deserialization format is as follows:
	//
	// Version 1:
	//  ------------------------------------------------------------------------------
	// | version(4 bytes) | priKeyLen(4 bytes) | priKey | pubKeyLen(4 bytes) | pubKey |
	//  ------------------------------------------------------------------------------
	// The public key field is optional. If there is no public key, then the pubKeyLen would be set to 0.
	//
	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		ByteBuffer buf = ByteBuffer.wrap(data);

		byte[] ver = new byte[curVersion.getSeriaizedSize()];
		buf.get(ver);
		RsaKeyVer version = RsaKeyVer.Deserialize(ver);

		switch(version) {
		case ONE:
			byte[] pri = new byte[buf.getInt()];
			buf.get(pri);
			byte[] pub = new byte[buf.getInt()];
			buf.get(pub);

			RsaKeyPri prikey = RsaKeyPri.Deserialize(pri);
			setPrivateKey(prikey);

			try {
				RsaKeyPub pubkey = RsaKeyPub.Deserialize(pub);
				setPublicKey(pubkey);
			} catch (StrongDocKeyException e) {
				RsaKeyPub pubkey = prikey.generatePublic();
				prikey.setPublicKey(pubkey.getKey());
				setPublicKey(pubkey);
			}

			return (T) this;
		default:
			throw new StrongDocKeyException("Unknown key version " + version.getVersion().getVersion());
		}
	}

	@Override
	public byte[] serialize() {
		final byte[] ver = curVersion.serialize();
		final byte[] pri = getPrivateKey().serialize();
		final byte[] pub = getPublicKey().serialize();

		ByteBuffer buf = ByteBuffer.allocate(ver.length + 4 + pri.length + 4 + pub.length);
		buf.put(ver);
		buf.putInt(pri.length);
		buf.put(pri);
		buf.putInt(pub.length);
		buf.put(pub);
		return buf.array();
	}

	@Override
	@SuppressWarnings("unchecked")
	public RsaKeyPri getPrivateKey() {
		return priKey;
	}

	private void setPrivateKey(RsaKeyPri priKey) {
		this.priKey = priKey;
	}

	@Override
	@SuppressWarnings("unchecked")
	public RsaKeyPub getPublicKey() {
		return pubKey;
	}

	private void setPublicKey(RsaKeyPub pubKey) {
		this.pubKey = pubKey;
	}

	public RsaKeySize getKeySize() {
		int keySize = ((RSAKey)getPrivateKey().getKey()).getModulus().bitLength();
		return RsaKeySize.parseKeySize(keySize);
	}
}

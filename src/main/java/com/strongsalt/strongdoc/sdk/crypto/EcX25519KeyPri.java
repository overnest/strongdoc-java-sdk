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

import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.Box;
import com.goterl.lazycode.lazysodium.utils.Key;

public class EcX25519KeyPri implements KeyPrivate {
	private static LazySodiumJava ls = new LazySodiumJava(new SodiumJava());
	private static Box.Native nbox = ls;

	private Key priKey;
	private Key pubKey;

	protected EcX25519KeyPri() {}

	protected EcX25519KeyPri(final byte[] pri) {
		this.setKey(Key.fromBytes(pri));
	}

	protected EcX25519KeyPri(final byte[] pri, final byte[] pub) {
		this.setKey(Key.fromBytes(pri));
		this.setPublicKey(Key.fromBytes(pub));
	}

	@Override
	public byte[] serialize() {
		return (getKey() == null || getKey().getAsBytes() == null ? new byte[0] :
			getKey().getAsBytes());	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		int len = data == null ? 0 : data.length;
		if (len != Box.SECRETKEYBYTES) {
			throw new StrongDocKeyException("Key length " + len + " is invalid");
		}
		setKey(Key.fromBytes(data));
		return (T) this;
	}

	public static EcX25519KeyPri Deserialize(final byte[] data) throws StrongDocKeyException {
		EcX25519KeyPri key = new EcX25519KeyPri();
		return key.deserialize(data);
	}

	@Override
	public byte[] decrypt(final byte[] ciphertext) throws StrongDocKeyException {
		if (ciphertext == null || ciphertext.length < Box.SEALBYTES) {
			throw new StrongDocKeyException("Invalid ciphertext format");
		}

		if (getPublicKey() == null) {
			setPublicKey(generatePublic().getKey());
		}

		final byte[] plaintext = new byte[ciphertext.length - Box.SEALBYTES];
		if (!nbox.cryptoBoxSealOpen(plaintext, ciphertext, ciphertext.length, getPublicKey().getAsBytes(),
				getPrivateKey().getAsBytes())) {
			throw new StrongDocKeyException("Could not decrypt ciphertext");
		}
		return plaintext;
	}

	public EcX25519KeyPub generatePublic() throws StrongDocKeyException {
		final byte[] pubKey = new byte[Box.PUBLICKEYBYTES];
		if (!ls.cryptoScalarMultBase(pubKey, getKey().getAsBytes())) {
			throw new StrongDocKeyException("Can not generate public key from private key");
		}
		return new EcX25519KeyPub(pubKey);
	}

	public Key getKey() {
		return getPrivateKey();
	}

	private void setKey(Key key) {
		setPrivateKey(key);
	}

	public Key getPrivateKey() {
		return priKey;
	}

	private void setPrivateKey(Key key) {
		this.priKey = key;
	}

	public Key getPublicKey() {
		return pubKey;
	}

	protected void setPublicKey(Key pubKey) {
		this.pubKey = pubKey;
	}

}

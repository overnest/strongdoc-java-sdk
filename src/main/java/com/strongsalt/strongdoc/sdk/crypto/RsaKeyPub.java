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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import com.strongsalt.strongdoc.sdk.crypto.RsaKey.RsaKeySize;

public class RsaKeyPub implements KeyPublic {
	private PublicKey key;

	protected RsaKeyPub() {}
	protected RsaKeyPub(final PublicKey pub) {
		this.setKey(pub);
	}

	public static RsaKeyPub Deserialize(final byte[] data) throws StrongDocKeyException {
		RsaKeyPub key = new RsaKeyPub();
		return key.deserialize(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		try {
			setKey(KeyFactory.getInstance("RSA").
					generatePublic(new X509EncodedKeySpec(data)));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			throw new StrongDocKeyException("Can not deserialize RSA key", e);
		}
		return (T) this;
	}

	@Override
	public byte[] serialize() {
		 X509EncodedKeySpec spec = new X509EncodedKeySpec(getKey().getEncoded());
		 return spec.getEncoded();
	}

	@Override
	public byte[] encrypt(final byte[] plaintext) throws StrongDocKeyException {
		try {
			Cipher rsa = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
			OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1",
					new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
			rsa.init(Cipher.ENCRYPT_MODE, getKey(), oaepParams);
			return rsa.doFinal(plaintext);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new StrongDocKeyException("Can not encrypt ciphertext", e);
		}
	}


	public PublicKey getKey() {
		return key;
	}

	private void setKey(PublicKey key) {
		this.key = key;
	}

	public RsaKeySize getKeySize() {
		int keySize = ((RSAKey)getKey()).getModulus().bitLength();
		return RsaKeySize.parseKeySize(keySize);
	}
}

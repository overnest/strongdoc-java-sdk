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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import com.strongsalt.strongdoc.sdk.crypto.RsaKey.RsaKeySize;

public class RsaKeyPri implements KeyPrivate {

	private PrivateKey priKey;
	private PublicKey pubKey;

	protected RsaKeyPri() {}
	protected RsaKeyPri(final PrivateKey pri) {
		this.setKey(pri);
	}
	protected RsaKeyPri(PrivateKey pri, PublicKey pub) {
		this.setKey(pri);
		this.setPublicKey(pub);
	}

	@Override
	public byte[] serialize() {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(getKey().getEncoded());
		return spec.getEncoded();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		try {
			setKey(KeyFactory.getInstance("RSA").
					generatePrivate(new PKCS8EncodedKeySpec(data)));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			throw new StrongDocKeyException("Can not deserialize RSA key", e);
		}
		return (T) this;
	}

	public static RsaKeyPri Deserialize(final byte[] data) throws StrongDocKeyException {
		RsaKeyPri key = new RsaKeyPri();
		return key.deserialize(data);
	}

	@Override
	public byte[] decrypt(final byte[] ciphertext) throws StrongDocKeyException {
		try {
			Cipher rsa = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
			OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1",
					new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
			rsa.init(Cipher.DECRYPT_MODE, getKey(), oaepParams);
			return rsa.doFinal(ciphertext);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new StrongDocKeyException("Can not decrypt ciphertext", e);
		}
	}

	public RsaKeyPub generatePublic() throws StrongDocKeyException {
	    RSAPrivateCrtKey privk = (RSAPrivateCrtKey)getPrivateKey();
	    RSAPublicKeySpec publicKeySpec = new java.security.spec.RSAPublicKeySpec(
	    		privk.getModulus(), privk.getPublicExponent());
	    KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		    return new RsaKeyPub(keyFactory.generatePublic(publicKeySpec));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new StrongDocKeyException("Can not generate public RSA key", e);
		}
	}

	public PrivateKey getKey() {
		return getPrivateKey();
	}

	private void setKey(PrivateKey key) {
		setPrivateKey(key);
	}

	public PrivateKey getPrivateKey() {
		return priKey;
	}

	private void setPrivateKey(PrivateKey key) {
		this.priKey = key;
	}

	public PublicKey getPublicKey() {
		return pubKey;
	}

	protected void setPublicKey(PublicKey pubKey) {
		this.pubKey = pubKey;
	}

	public RsaKeySize getKeySize() {
		int keySize = ((RSAKey)getPrivateKey()).getModulus().bitLength();
		return RsaKeySize.parseKeySize(keySize);
	}
}

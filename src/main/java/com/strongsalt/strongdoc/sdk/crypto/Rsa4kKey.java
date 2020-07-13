package com.strongsalt.strongdoc.sdk.crypto;

import java.security.KeyPair;

public class Rsa4kKey extends RsaKey {
	private Rsa4kKey() {
		super();
	}

	private Rsa4kKey(RsaKeyPub pubKey, RsaKeyPri priKey){
		super(pubKey, priKey);
	}

	public static Rsa4kKey GenerateKey() throws StrongDocKeyException {
		KeyPair pair = RsaKey.GenerateKey(RsaKeySize.RSA4K);
		return new Rsa4kKey(
				new RsaKeyPub(pair.getPublic()),
				new RsaKeyPri(pair.getPrivate(), pair.getPublic()));
	}
}

package com.strongsalt.strongdoc.sdk.crypto;

import java.security.KeyPair;

public class Rsa2kKey extends RsaKey {
	private Rsa2kKey() {
		super();
	}

	private Rsa2kKey(RsaKeyPub pubKey, RsaKeyPri priKey){
		super(pubKey, priKey);
	}

	public static Rsa2kKey GenerateKey() throws StrongDocKeyException {
		KeyPair pair = RsaKey.GenerateKey(RsaKeySize.RSA2K);
		return new Rsa2kKey(
				new RsaKeyPub(pair.getPublic()),
				new RsaKeyPri(pair.getPrivate(), pair.getPublic()));
	}
}

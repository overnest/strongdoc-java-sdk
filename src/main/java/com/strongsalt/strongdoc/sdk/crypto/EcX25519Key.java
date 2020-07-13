package com.strongsalt.strongdoc.sdk.crypto;

import java.nio.ByteBuffer;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.Box;

public class EcX25519Key implements KeyAsymmetric {

	private static LazySodiumJava ls = new LazySodiumJava(new SodiumJava());
	private static Box.Native nbox = ls;

	private EcX25519KeyPub pubKey;
	private EcX25519KeyPri priKey;
	private EcX25519KeyVer curVersion = EcX25519KeyVer.ONE;

	public enum EcX25519KeyVer implements VersionInterface {
		ONE(1);

		private Version version;
		static {
			VersionInterface.setClassVersions(EcX25519KeyVer.class.getName(), EcX25519KeyVer.values());
		}

		private EcX25519KeyVer() {}
		private EcX25519KeyVer(final int version) {
			this.version = new Version(version);
		}

		public static EcX25519KeyVer Deserialize(final byte[] data) throws StrongDocKeyException {
			return VersionInterface.Deserialize(EcX25519KeyVer.class, data);
		}

		@Override
		public Version getVersion() {
			return version;
		}
	}

	private EcX25519Key() {}
	private EcX25519Key(EcX25519KeyPub pubKey, EcX25519KeyPri priKey){
		this.setPrivateKey(priKey);
		this.setPublicKey(pubKey);
	}

	public static EcX25519Key GenerateKey() throws StrongDocKeyException {
		final byte[] pubKey = new byte[Box.PUBLICKEYBYTES];
		final byte[] priKey = new byte[Box.SECRETKEYBYTES];

		if (!nbox.cryptoBoxKeypair(pubKey, priKey)) {
			throw new StrongDocKeyException("Can not generate new " + EcX25519Key.class.getName() + " key");
		}

		return new EcX25519Key(new EcX25519KeyPub(pubKey), new EcX25519KeyPri(priKey, pubKey));
	}

	@Override
	public byte[] encrypt(byte[] plaintext) throws StrongDocKeyException {
		return this.getPublicKey().encrypt(plaintext);
	}

	@Override
	public byte[] decrypt(byte[] ciphertext) throws StrongDocKeyException {
		return this.getPrivateKey().decrypt(ciphertext);
	}

	public static EcX25519Key Deserialize(final byte[] data) throws StrongDocKeyException {
		EcX25519Key key = new EcX25519Key();
		return key.deserialize(data);
	}

	//
	// The serialization/deserialization format is as follows:
	//
	//  -------------------------
	// | version(4 bytes) | data |
	//  -------------------------
	//
	// Version 1 data format:
	//  -----------------------------------------------------------
	// | priKeyLen(4 bytes) | priKey | pubKeyLen(4 bytes) | pubKey |
	//  -----------------------------------------------------------
	// The public key field is optional. If there is no public key, then the pubKeyLen would be set to 0.
	//
	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		ByteBuffer buf = ByteBuffer.wrap(data);

		byte[] ver = new byte[curVersion.getSeriaizedSize()];
		buf.get(ver);
		EcX25519KeyVer version = EcX25519KeyVer.Deserialize(ver);

		switch(version) {
		case ONE:
			byte[] pri = new byte[buf.getInt()];
			buf.get(pri);
			EcX25519KeyPri prikey = EcX25519KeyPri.Deserialize(pri);
			setPrivateKey(prikey);

			byte[] pub = new byte[buf.getInt()];
			buf.get(pub);

			try {
				EcX25519KeyPub pubkey = EcX25519KeyPub.Deserialize(pub);
				setPublicKey(pubkey);
			} catch (StrongDocKeyException e) {
				EcX25519KeyPub pubkey = prikey.generatePublic();
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
	public EcX25519KeyPri getPrivateKey() {
		return priKey;
	}

	private void setPrivateKey(EcX25519KeyPri priKey) {
		this.priKey = priKey;
	}

	@Override
	@SuppressWarnings("unchecked")
	public EcX25519KeyPub getPublicKey() {
		return pubKey;
	}

	private void setPublicKey(EcX25519KeyPub pubKey) {
		this.pubKey = pubKey;
	}
}

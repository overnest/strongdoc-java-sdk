package com.strongsalt.strongdoc.sdk.crypto;

import java.nio.ByteBuffer;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.AEAD;

public class XChaCha20Key implements KeySymmetric {
	private static LazySodiumJava ls = new LazySodiumJava(new SodiumJava());
	private static AEAD.Native naead = ls;

	private byte[] key;
	private XChaCha20KeyVer curVersion = XChaCha20KeyVer.ONE;

	public enum XChaCha20KeyVer implements VersionInterface {
		ONE(1);

		private Version version;
		static {
			VersionInterface.setClassVersions(XChaCha20KeyVer.class.getName(), XChaCha20KeyVer.values());
		}

		private XChaCha20KeyVer() {}
		private XChaCha20KeyVer(final int version) {
			this.version = new Version(version);
		}

		public static XChaCha20KeyVer Deserialize(final byte[] data) throws StrongDocKeyException {
			return VersionInterface.Deserialize(XChaCha20KeyVer.class, data);
		}

		@Override
		public Version getVersion() {
			return version;
		}
	}

	protected XChaCha20Key() {}
	protected XChaCha20Key(byte[] key){
		this.setKey(key);
	}

	public static XChaCha20Key GenerateKey() throws StrongDocKeyException {
		final byte[] key = new byte[AEAD.XCHACHA20POLY1305_IETF_KEYBYTES];
		naead.cryptoAeadXChaCha20Poly1305IetfKeygen(key);
		return new XChaCha20Key(key);
	}

	@Override
	public byte[] encrypt(byte[] plaintext) throws StrongDocKeyException {
		if (plaintext == null) {
			plaintext = new byte[0];
		}
		final byte[] nonce = ls.randomBytesBuf(AEAD.XCHACHA20POLY1305_IETF_NPUBBYTES);
		final byte[] ciphertext = new byte[AEAD.XCHACHA20POLY1305_IETF_ABYTES + plaintext.length];
		if (!naead.cryptoAeadXChaCha20Poly1305IetfEncrypt(
				ciphertext, new long[] {ciphertext.length},
				plaintext, plaintext.length, null, 0,
				null, nonce, getKey())) {
			throw new StrongDocKeyException("Could not encrypt plaintext");
		}

		return StrongDocUtils.ArrayConcat(nonce, ciphertext);
	}

	@Override
	public byte[] decrypt(byte[] ciphertext) throws StrongDocKeyException {
		if (ciphertext == null || ciphertext.length < AEAD.XCHACHA20POLY1305_IETF_NPUBBYTES +
				AEAD.XCHACHA20POLY1305_IETF_ABYTES) {
			throw new StrongDocKeyException("Invalid ciphertext format");
		}

		final byte[] nonce = new byte[AEAD.XCHACHA20POLY1305_IETF_NPUBBYTES];
		final byte[] cipher = new byte[ciphertext.length - AEAD.XCHACHA20POLY1305_IETF_NPUBBYTES];
		final byte[] plaintext = new byte[cipher.length - AEAD.XCHACHA20POLY1305_IETF_ABYTES];

		try {
			StrongDocUtils.ArraySplit(ciphertext, nonce, cipher);
		} catch (StrongDocException e) {
			throw new StrongDocKeyException("Invalid ciphertext format", e);
		}

		if (!naead.cryptoAeadXChaCha20Poly1305IetfDecrypt(
				plaintext, new long[] {plaintext.length}, null,
				cipher, cipher.length, null, 0, nonce, getKey())) {
			throw new StrongDocKeyException("Could not encrypt ciphertext");
		}

		return plaintext;
	}

	public static XChaCha20Key Deserialize(final byte[] data) throws StrongDocKeyException {
		XChaCha20Key key = new XChaCha20Key();
		return key.deserialize(data);
	}

	//
	// The serialization/deserialization format is as follows:
	//
	// Version 1:
	//  ------------------------------------------
	// | version(4 bytes) | keyLen(4 bytes) | key |
	//  ------------------------------------------
	//
	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		ByteBuffer buf = ByteBuffer.wrap(data);

		byte[] ver = new byte[curVersion.getSeriaizedSize()];
		buf.get(ver);
		XChaCha20KeyVer version = XChaCha20KeyVer.Deserialize(ver);

		switch(version) {
		case ONE:
			byte[] key = new byte[buf.getInt()];
			buf.get(key);
			setKey(key);
			return (T) this;
		default:
			throw new StrongDocKeyException("Unknown key version " + version.getVersion().getVersion());
		}
	}

	@Override
	public byte[] serialize() {
		return serialize(curVersion.serialize());
	}

	@Override
	public byte[] getKey() {
		return this.key;
	}

	private void setKey(final byte[] key) {
		this.key = key;
	}
}

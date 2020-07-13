package com.strongsalt.strongdoc.sdk.crypto;

import java.nio.ByteBuffer;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.AEAD;

public class AesGcmKey implements KeySymmetric {
	private static LazySodiumJava ls = new LazySodiumJava(new SodiumJava());
	private static AEAD.Native naead = ls;

	private byte[] key;
	private AesGcmKeyVer curVersion = AesGcmKeyVer.ONE;

	public enum AesGcmKeyVer implements VersionInterface {
		ONE(1);

		private Version version;
		static {
			VersionInterface.setClassVersions(AesGcmKeyVer.class.getName(), AesGcmKeyVer.values());
		}

		private AesGcmKeyVer() {}
		private AesGcmKeyVer(final int version) {
			this.version = new Version(version);
		}

		public static AesGcmKeyVer Deserialize(final byte[] data) throws StrongDocKeyException {
			return VersionInterface.Deserialize(AesGcmKeyVer.class, data);
		}

		@Override
		public Version getVersion() {
			return version;
		}
	}

	protected AesGcmKey() {}
	protected AesGcmKey(byte[] key){
		this.setKey(key);
	}

	public static AesGcmKey GenerateKey() throws StrongDocKeyException {
		final byte[] key = new byte[AEAD.AES256GCM_KEYBYTES];
		naead.cryptoAeadAES256GCMKeygen(key);
		return new AesGcmKey(key);
	}

	@Override
	public byte[] encrypt(byte[] plaintext) throws StrongDocKeyException {
		if (plaintext == null) {
			plaintext = new byte[0];
		}
		final byte[] nonce = ls.randomBytesBuf(AEAD.AES256GCM_NPUBBYTES);
		final byte[] ciphertext = new byte[AEAD.AES256GCM_ABYTES + plaintext.length];
		if (!naead.cryptoAeadAES256GCMEncrypt(
				ciphertext, new long[] {ciphertext.length},
				plaintext, plaintext.length, null, 0,
				null, nonce, getKey())) {
			throw new StrongDocKeyException("Could not encrypt plaintext");
		}

		return StrongDocUtils.ArrayConcat(nonce, ciphertext);
	}

	@Override
	public byte[] decrypt(byte[] ciphertext) throws StrongDocKeyException {
		if (ciphertext == null || ciphertext.length < AEAD.AES256GCM_NPUBBYTES +
				AEAD.AES256GCM_ABYTES) {
			throw new StrongDocKeyException("Invalid ciphertext format");
		}

		final byte[] nonce = new byte[AEAD.AES256GCM_NPUBBYTES];
		final byte[] cipher = new byte[ciphertext.length - AEAD.AES256GCM_NPUBBYTES];
		final byte[] plaintext = new byte[cipher.length - AEAD.AES256GCM_ABYTES];

		try {
			StrongDocUtils.ArraySplit(ciphertext, nonce, cipher);
		} catch (StrongDocException e) {
			throw new StrongDocKeyException("Invalid ciphertext format", e);
		}

		if (!naead.cryptoAeadAES256GCMDecrypt(
				plaintext, new long[] {plaintext.length}, null,
				cipher, cipher.length, null, 0, nonce, getKey())) {
			throw new StrongDocKeyException("Could not encrypt ciphertext");
		}

		return plaintext;
	}

	public static AesGcmKey Deserialize(final byte[] data) throws StrongDocKeyException {
		AesGcmKey key = new AesGcmKey();
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
		AesGcmKeyVer version = AesGcmKeyVer.Deserialize(ver);

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

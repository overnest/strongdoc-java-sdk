package com.strongsalt.strongdoc.sdk.crypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.AEAD;
import com.goterl.lazycode.lazysodium.interfaces.SecretStream;
import com.goterl.lazycode.lazysodium.interfaces.Stream;
import com.goterl.lazycode.lazysodium.interfaces.StreamJava;

public class XChaCha20StreamKey implements KeySymmetric {
	private static LazySodiumJava ls = new LazySodiumJava(new SodiumJava());
	private static StreamJava.Native nstream = ls;

	private byte[] key;
	private XChaCha20StreamKeyVer curVersion = XChaCha20StreamKeyVer.ONE;

	public enum XChaCha20StreamKeyVer implements VersionInterface {
		ONE(1);

		private Version version;
		static {
			VersionInterface.setClassVersions(XChaCha20StreamKeyVer.class.getName(), XChaCha20StreamKeyVer.values());
		}

		private XChaCha20StreamKeyVer() {}
		private XChaCha20StreamKeyVer(final int version) {
			this.version = new Version(version);
		}

		public static XChaCha20StreamKeyVer Deserialize(final byte[] data) throws StrongDocKeyException {
			return VersionInterface.Deserialize(XChaCha20StreamKeyVer.class, data);
		}

		@Override
		public Version getVersion() {
			return version;
		}
	}

	protected XChaCha20StreamKey() {}
	protected XChaCha20StreamKey(byte[] key){
		this.setKey(key);
	}

	public static XChaCha20StreamKey GenerateKey() throws StrongDocKeyException {
		final byte[] key = new byte[StreamJava.XCHACHA20_KEYBYTES];
		nstream.cryptoStreamXChaCha20Keygen(key);
		return new XChaCha20StreamKey(key);
	}

	@Override
	public byte[] encrypt(byte[] plaintext) throws StrongDocKeyException {
		if (plaintext == null) {
			plaintext = new byte[0];
		}
		final byte[] nonce = ls.randomBytesBuf(StreamJava.XCHACHA20_NONCEBYTES);
		final byte[] ciphertext = new byte[plaintext.length];
		if (!nstream.cryptoStreamXChaCha20Xor(ciphertext, plaintext,
				plaintext.length, nonce, getKey())) {
			throw new StrongDocKeyException("Could not encrypt plaintext");
		}

		return StrongDocUtils.ArrayConcat(nonce, ciphertext);
	}


//	public OutputStream encrypt(InputStream plainStream) {
//
//	}
//
//	public OutputStream encrypt(InputStream plainStream, long offset) throws StrongDocKeyException {
//		if (plainStream == null) {
//			return new byte[0];
//		}
//		final byte[] nonce = ls.randomBytesBuf(StreamJava.XCHACHA20_NONCEBYTES);
//		final byte[] ciphertext = new byte[plaintext.length];
//		if (!nstream.cryptoStreamXChaCha20Xor(ciphertext, plaintext,
//				plaintext.length, nonce, getKey())) {
//			throw new StrongDocKeyException("Could not encrypt plaintext");
//		}
//
//		return StrongDocUtils.ArrayConcat(nonce, ciphertext);
//	}

	@Override
	public byte[] decrypt(byte[] ciphertext) throws StrongDocKeyException {
		if (ciphertext == null || ciphertext.length < StreamJava.XCHACHA20_NONCEBYTES) {
			throw new StrongDocKeyException("Invalid ciphertext format");
		}

		final byte[] nonce = new byte[StreamJava.XCHACHA20_NONCEBYTES];
		final byte[] cipher = new byte[ciphertext.length - StreamJava.XCHACHA20_NONCEBYTES];
		final byte[] plaintext = new byte[cipher.length];

		try {
			StrongDocUtils.ArraySplit(ciphertext, nonce, cipher);
		} catch (StrongDocException e) {
			throw new StrongDocKeyException("Invalid ciphertext format", e);
		}

		if (!nstream.cryptoStreamXChaCha20Xor(plaintext, cipher, cipher.length,
				nonce, getKey())) {
			throw new StrongDocKeyException("Could not encrypt ciphertext");
		}

		return plaintext;
	}

	public static XChaCha20StreamKey Deserialize(final byte[] data) throws StrongDocKeyException {
		XChaCha20StreamKey key = new XChaCha20StreamKey();
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
		XChaCha20StreamKeyVer version = XChaCha20StreamKeyVer.Deserialize(ver);

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

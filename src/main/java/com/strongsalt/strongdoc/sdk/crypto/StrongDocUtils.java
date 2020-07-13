package com.strongsalt.strongdoc.sdk.crypto;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.Box;

public class StrongDocUtils {
	public static byte[] ArrayConcat(final byte[]... arrays) {
		if (arrays == null) {
			return new byte[0];
		}

		int bytes = 0;
		for (byte[] a: arrays) {
			if (a != null) {
				bytes += a.length;
			}
		}

		ByteBuffer buf = ByteBuffer.allocate(bytes);
		for (byte[] a: arrays) {
			if (a != null) {
				buf.put(a);
			}
		}

		return buf.array();
	}

	public static boolean ArraySplit(final byte[] array, final byte[]... arrays) throws StrongDocException {
		boolean success = false;

		if (arrays == null) {
			return success;
		}

		ByteBuffer buf = ByteBuffer.wrap(array);
		for (final byte[] a: arrays) {
			try {
				buf.get(a);
			} catch (BufferUnderflowException e) {
				throw new StrongDocException("Not enough input bytes for total output array", e);
			}
		}

		return !buf.hasRemaining();
	}
}

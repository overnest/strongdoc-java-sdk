package com.strongsalt.strongdoc.sdk.utils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;

public class StrongDocUtils {
    public static String encodeWithBase64(byte[] input){
        return Base64.getUrlEncoder().encodeToString(input);
    }

    public static byte[] decodeWithBase64(String input){
        return Base64.getUrlDecoder().decode(input);
    }

    public static byte[] arrayCopy(byte[] original, int from, int to) {
        byte[] res = new byte[to-from];
        System.arraycopy(original, from, res, 0, to-from);
        return res;
    }

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
}

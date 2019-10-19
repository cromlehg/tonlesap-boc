package com.blockwit.tonlesap.boc;

public class Utils {

    public static int getInt(byte[] bs, int n, int size) {
        int value = 0;
        for (int i = 0; i < size; i++) {
            int idx = (n + i) / 8;
            int bitsIdx = (n + i) % 8;
            value += getBit(bs[idx], bitsIdx) * Math.pow(2, i);
        }
        return value;
    }

    public static int getInt(byte b, int n, int size) {
        int value = 0;
        for (int i = 0; i < size; i++) {
            value += getBit(b, n + i) * Math.pow(2, size - 1 - i);
        }
        return value;
    }

    public static boolean getBoolBit(byte b, int n) {
        return getBit(b, n) == 1;
    }

    public static int getBit(byte b, int n) {
        return ((b >> (n - 1)) & 1);
    }

    public static int uintFromByteArray(byte[] bytes, int offset, int size) {
        if (size == 4)
            return ((bytes[offset] & 0xFF) << 24) | ((bytes[offset + 1] & 0xFF) << 16)
                    | ((bytes[offset + 2] & 0xFF) << 8) | ((bytes[offset + 3] & 0xFF) << 0);
        if (size == 3)
            return ((bytes[offset] & 0xFF) << 16) | ((bytes[offset + 1] & 0xFF) << 8)
                    | ((bytes[offset + 2] & 0xFF) << 0);
        if (size == 2)
            return ((bytes[offset] & 0xFF) << 8) | ((bytes[offset + 1] & 0xFF) << 0);
        if (size == 1)
            return ((bytes[offset] & 0xFF) << 0);

        if (size == 0)
            return 0;

        throw new UnsupportedOperationException("Size must be from 0 to 4");
    }

    public static String intArrayToString(int[] a) {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(a[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public static String byteArrayToHexString(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        sb.append("0x");
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static String byteArrayToHexString(byte[] a, int cuttedSize) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        sb.append("0x");
        for (byte b : a)
            sb.append(String.format("%02x", b));

        if (sb.length() > cuttedSize)
            return sb.substring(0, cuttedSize) + "...";

        return sb.toString();
    }

}

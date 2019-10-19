package com.blockwit.tonlesap.boc;

public class BOCFormatConsts {

    public static final byte[] magic = new byte[]{(byte) 0xB5, (byte) 0xEE, (byte) 0x9C, (byte) 0x72};

    public static final int MAGIC_SIZE = magic.length;

    public static final int IDX_HEADER_BYTE = MAGIC_SIZE;

    public static final int SIZE_HEADER_BYTE = 1;

    public static final int IDX_OFF_BYTES = IDX_HEADER_BYTE + SIZE_HEADER_BYTE;

    public static final int SIZE_OFF_BYTES = 1;

    public static final int IDX_CELLS = IDX_OFF_BYTES + SIZE_OFF_BYTES;

    public static final int IDX_BITS_has_idx = 0;
    public static final int SIZE_BITS_has_idx = 1;

    public static final int IDX_BITS_has_crc32c = IDX_BITS_has_idx + SIZE_BITS_has_idx;
    public static final int SIZE_BITS_has_crc32c = 1;

    public static final int IDX_BITS_has_cache_bits = IDX_BITS_has_crc32c + SIZE_BITS_has_crc32c;
    public static final int SIZE_BITS_has_cache_bits = 1;

    public static final int IDX_BITS_flags = IDX_BITS_has_cache_bits + SIZE_BITS_has_cache_bits;
    public static final int SIZE_BITS_flags = 2;

    public static final int IDX_BITS_size = IDX_BITS_flags + SIZE_BITS_flags;
    public static final int SIZE_BITS_size = 3;

}

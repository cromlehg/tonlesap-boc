package com.blockwit.tonlesap.boc;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BOCDeserializer {


    public static final BOC deserializeFromBytes(byte[] bytes) throws BOCFormatException {
        BOC boc = new BOC();
        byte headerByte = bytes[BOCFormatConsts.IDX_HEADER_BYTE];
        boc.has_idx = Utils.getBoolBit(headerByte, BOCFormatConsts.IDX_BITS_has_idx);
        boc.has_crc32c = Utils.getBoolBit(headerByte, BOCFormatConsts.IDX_BITS_has_crc32c);
        boc.has_cache_bits = Utils.getBoolBit(headerByte, BOCFormatConsts.IDX_BITS_has_cache_bits);
        boc.flags = Utils.getInt(headerByte, BOCFormatConsts.IDX_BITS_flags, BOCFormatConsts.SIZE_BITS_flags);
        if (!(boc.flags == 0))
            throw new BOCFormatException("FLags must be 0");

        boc.size = Utils.getInt(headerByte, BOCFormatConsts.IDX_BITS_size, BOCFormatConsts.SIZE_BITS_size);
        if (!(boc.size <= 4))
            throw new BOCFormatException("Size must be <= 4");

        boc.off_bytes = bytes[BOCFormatConsts.IDX_OFF_BYTES];
        if (!(boc.off_bytes <= 8))
            throw new BOCFormatException("Off_bytes must be <= 8");

        int curIndex = BOCFormatConsts.IDX_CELLS;
        boc.cells = Utils.uintFromByteArray(bytes, curIndex, boc.size);
        curIndex += boc.size;

        boc.roots = Utils.uintFromByteArray(bytes, curIndex, boc.size);
        if (!(boc.roots >= 1))
            throw new BOCFormatException("Roots must be >= 1");

        curIndex += boc.size;
        boc.absent = Utils.uintFromByteArray(bytes, curIndex, boc.size);
        if (!(boc.roots + boc.absent <= boc.cells))
            throw new BOCFormatException("roots + absent must be <= cells");

        curIndex += boc.size;
        boc.tot_cells_size = Utils.uintFromByteArray(bytes, curIndex, boc.off_bytes);

        curIndex += boc.off_bytes;

        boc.root_list = new int[boc.roots];
        for (int i = 0; i < boc.roots; i++) {
            boc.root_list[i] = Utils.uintFromByteArray(bytes, curIndex, boc.size);
            curIndex += boc.size;
        }

        if (boc.has_idx) {
            boc.index = new int[boc.cells];
            for (int i = 0; i < boc.cells; i++) {
                boc.index[i] = Utils.uintFromByteArray(bytes, curIndex, boc.off_bytes);
                curIndex += boc.off_bytes;
            }
        } else {
            boc.index = new int[0];
        }

        boc.cell_data = new byte[boc.tot_cells_size];
        System.arraycopy(bytes, curIndex, boc.cell_data, 0, boc.tot_cells_size);

        curIndex += boc.tot_cells_size;

        if (boc.has_crc32c) {
            boc.crc32c = new byte[4];
            for (int i = 0; i < 4; i++) {
                boc.crc32c[i] = bytes[curIndex];
                curIndex += 1;
            }
        } else {
            boc.crc32c = new byte[0];
        }

        if (curIndex != bytes.length)
            throw new BOCFormatException("BOC bytes size must be equals to calculated size! Bytes size now " + bytes.length + ". Calculated " + curIndex);

        return boc;
    }

    public static final BOC deserializeFromFile(String path) throws BOCFormatException, IOException {
        return deserializeFromBytes(FileUtils.readFileToByteArray(new File(path)));
    }


}

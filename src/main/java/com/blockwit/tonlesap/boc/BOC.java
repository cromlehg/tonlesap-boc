package com.blockwit.tonlesap.boc;

/**
 * serialized_boc#b5ee9c72 has_idx:(## 1) has_crc32c:(## 1)
 * has_cache_bits:(## 1) flags:(## 2) { flags = 0 }
 * size:(## 3) { size <= 4 }
 * off_bytes:(## 8) { off_bytes <= 8 }
 * cells:(##(size * 8))
 * roots:(##(size * 8)) { roots >= 1 }
 * absent:(##(size * 8)) { roots + absent <= cells }
 * tot_cells_size:(##(off_bytes * 8))
 * root_list:(roots * ##(size * 8))
 * index:has_idx?(cells * ##(off_bytes * 8))
 * cell_data:(tot_cells_size * [ uint8 ])
 * crc32c:has_crc32c?uint32
 * = BagOfCells;
 *
 * @author Alexander Strakh
 *
 */
public class BOC {


    public boolean has_idx;

    public boolean has_crc32c;

    public boolean has_cache_bits;

    public int flags;

    public int size;

    public int off_bytes;

    public int cells;

    public int roots;

    public int absent;

    // размер всех ячеек в байтах
    public int tot_cells_size;

    // содержит индексы корневых узлов (отсчет от нуля)
    public int[] root_list;

    public int[] index;

    public byte[] cell_data;

    public byte[] crc32c;

    // deserialized cells
    //public Cell[] dCells;

    @Override
    public String toString() {
        return "BOC{\n" +
                "\thas_idx: " + has_idx + ",\n" +
                "\thas_crc32c: " + has_crc32c + ",\n" +
                "\thas_cache_bits: " + has_cache_bits + ",\n" +
                "\tflags: " + flags + ",\n" +
                "\tsize: " + size + ",\n" +
                "\toff_bytes: " + off_bytes + ",\n" +
                "\tcells: " + cells + ",\n" +
                "\troots: " + roots + ",\n" +
                "\tabsent: " + absent + ",\n" +
                "\ttot_cells_size: " + tot_cells_size + ",\n" +
                "\troot_list: " + Utils.intArrayToString(root_list) + ",\n" +
                "\tcell_data: " + Utils.byteArrayToHexString(cell_data, 20) + ",\n" +
                (has_idx ? ("\tindex: " + Utils.intArrayToString(index) + ",\n") : "") +
                (has_crc32c ? ("\tcrc32c: " + Utils.byteArrayToHexString(crc32c) + ",\n") : "") +
                "}";
    }

}

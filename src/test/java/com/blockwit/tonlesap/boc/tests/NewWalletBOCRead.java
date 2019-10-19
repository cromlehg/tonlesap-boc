package com.blockwit.tonlesap.boc.tests;

import com.blockwit.tonlesap.boc.BOC;
import com.blockwit.tonlesap.boc.BOCDeserializer;
import com.blockwit.tonlesap.boc.BOCFormatException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class NewWalletBOCRead {

    public static void main(String[] args) throws IOException, BOCFormatException {
        String path = "new-wallet-query.boc";
        ClassLoader classLoader = NewWalletBOCRead.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        byte[] content = IOUtils.toByteArray(inputStream);
        BOC boc = BOCDeserializer.deserializeFromBytes(content);
        System.out.println(boc);
    }

}

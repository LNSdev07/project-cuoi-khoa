package com.t3h.ecommerce.utils;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Component
public class FileFactory {
    public static final String PATH_TEMPLATE = "D:\\ADMIN\\Documents\\TEMPLATE";

    public static File createFile(String fileName, Workbook workbook) throws Exception {
        workbook = new XSSFWorkbook();

        OutputStream out = new FileOutputStream(PATH_TEMPLATE + fileName);

        workbook.write(out);

        return ResourceUtils.getFile(PATH_TEMPLATE + fileName);

    }


}

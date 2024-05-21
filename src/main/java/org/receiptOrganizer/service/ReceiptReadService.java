package org.receiptOrganizer.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.receiptOrganizer.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptReadService {
    @Autowired
    OCRService ocrService;

    public Receipt scanReceipt(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        File temp = File.createTempFile("target",".jpg");
        try(OutputStream outputStream = new FileOutputStream(temp)) {
            IOUtils.copy(inputStream, outputStream);
        }
        String output = ocrService.readContentsFromImage(temp);
        temp.delete();
        return filterData(output);
    }

    private Receipt filterData(String str) {
        String[] lines = str.split("\\n");
        int itemsBeginIndex = 0;
        List<String> items = new ArrayList<>();
        for(int i=0; i<lines.length; i++) {
            if(lines[i].matches(".*\\.\\b[0-9][0-9]*\\b.*")) {
                if(itemsBeginIndex==0) itemsBeginIndex=i;
                items.add(lines[i]);
            }
        }
        StringBuilder desc= new StringBuilder();
        for(int i=0; i<itemsBeginIndex; i++) {
            desc.append(lines[i]);
        }

        return new Receipt(null, desc.toString(), items, LocalDateTime.now());
    }
}

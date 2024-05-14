package org.receiptOrganizer.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.receiptOrganizer.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDateTime;

@Service
public class ReceiptService {
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
        return new Receipt(null, output, LocalDateTime.now());
    }
}

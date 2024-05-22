package org.receiptOrganizer.service;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
import org.receiptOrganizer.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.leptonica.global.leptonica.pixDestroy;
import static org.bytedeco.leptonica.global.leptonica.pixRead;

@Component
public class OCRService {
    @Autowired
    AppConfig appConfig;

    String tessdataUri;

    public String readContentsFromImage(File file) throws IOException {
        tessdataUri = appConfig.getTessdataUri();
        BytePointer outText;
        TessBaseAPI api = new TessBaseAPI();

        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(tessdataUri, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

        // Open input image with leptonica library
        PIX image = pixRead(file.getAbsolutePath());
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        String content = outText.getString();

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        return content;
    }
}

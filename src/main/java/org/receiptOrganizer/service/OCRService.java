package org.receiptOrganizer.service;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
import org.receiptOrganizer.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.bytedeco.leptonica.global.leptonica.pixDestroy;
import static org.bytedeco.leptonica.global.leptonica.pixRead;

@Service
public class OCRService {
    @Autowired
    ResourceLoader resourceLoader;

    Resource resource;
    String imageUri;

    public Receipt getReceipt() {
        Receipt receipt = new Receipt();
        String content = readContentsFromImage();
        receipt.setName(content);
        return receipt;
    }

    private String readContentsFromImage() {
        BytePointer outText;
        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        try {
            resource = resourceLoader.getResource("classpath:target1.jpg");
            imageUri = resource.getURI().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Open input image with leptonica library
        PIX image = pixRead(imageUri);
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

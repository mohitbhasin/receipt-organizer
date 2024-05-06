package org.receiptOrganizer.repository;

import org.receiptOrganizer.model.Receipt;
import org.receiptOrganizer.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiptRepository {
    @Autowired
    OCRService ocrService;

    private final List<Receipt> receipts = new ArrayList<>();
    public ReceiptRepository() {

    }

    public List<Receipt> findAll() {
        receipts.add(ocrService.getReceipt());
        return receipts;
    }

    public Receipt findById(Integer id) {
        for(Receipt receipt: receipts) {
            if(receipt.getId().equals(id)) return receipt;
        }
        return new Receipt();
    }
}

package org.receiptOrganizer.controller;
import org.receiptOrganizer.model.Receipt;
import org.receiptOrganizer.repository.ReceiptRepository;
import org.receiptOrganizer.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/receipt")
@CrossOrigin()
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @Autowired
    ReceiptRepository repository;

    @GetMapping("")
    public List<Receipt> findAll() {
        return repository.findAll();
    }

    @PostMapping("")
    public Receipt save(MultipartFile file) throws IOException {
        Receipt receipt = receiptService.scanReceipt(file);
        return repository.save(receipt);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}

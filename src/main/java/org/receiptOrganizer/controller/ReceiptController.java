package org.receiptOrganizer.controller;
import org.receiptOrganizer.dao.ReceiptDAO;
import org.receiptOrganizer.dao.ItemDAO;
import org.receiptOrganizer.model.Receipt;
import org.receiptOrganizer.repository.ItemRepository;
import org.receiptOrganizer.repository.ReceiptRepository;
import org.receiptOrganizer.service.ReceiptReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/receipt")
@CrossOrigin()
public class ReceiptController {
    @Autowired
    ReceiptReadService receiptService;

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("")
    public List<ReceiptDAO> findAll() {
        return receiptRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<ItemDAO> findAllReceipt(@PathVariable Integer id) {
        return itemRepository.findByReceiptId(id);
    }

    @PostMapping("")
    public Receipt save(MultipartFile file) throws IOException {
        Receipt receipt = receiptService.scanReceipt(file);
        ReceiptDAO receiptDAO = new ReceiptDAO(null, receipt.getDescription(), receipt.getDateCreated());
        receiptDAO = receiptRepository.save(receiptDAO);
        List<ItemDAO> itemDAOList = new ArrayList<>();
        for(String item: receipt.getItems()) {
            itemDAOList.add(new ItemDAO(null, receiptDAO.id(), item));
        }
        itemRepository.saveAll(itemDAOList);
        return receipt;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        receiptRepository.deleteById(id);
    }
}

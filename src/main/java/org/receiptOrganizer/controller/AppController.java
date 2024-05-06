package org.receiptOrganizer.controller;

import org.receiptOrganizer.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {
    @Autowired
    private ReceiptRepository repository;

    @GetMapping("")
    public String index() {
        return repository.findAll().toString();
    }
}

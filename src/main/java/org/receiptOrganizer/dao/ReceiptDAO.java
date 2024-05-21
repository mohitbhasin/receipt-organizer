package org.receiptOrganizer.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("RECEIPT")
public record ReceiptDAO(
        @Id
        Integer id,
        String description,
        LocalDateTime dateCreated) {
}

package org.receiptOrganizer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("RECEIPT")
public record Receipt(
        @Id
        Integer id,
        String content,
        LocalDateTime dateCreated) {
}

package org.receiptOrganizer.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("ITEM")
public record ItemDAO(
        @Id
        Integer id,
        Integer receiptId,
        String itemName){
}

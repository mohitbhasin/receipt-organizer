package org.receiptOrganizer.model;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt {
    Integer id;
    String description;
    List<String> items;
    LocalDateTime dateCreated;

    public Receipt(Integer id, String description, List<String> items, LocalDateTime dateCreated) {
        this.id = id;
        this.description = description;
        this.items = items;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

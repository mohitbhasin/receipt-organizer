package org.receiptOrganizer.repository;

import org.receiptOrganizer.dao.ItemDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ItemRepository extends ListCrudRepository<ItemDAO, Integer> {
    List<ItemDAO> findByReceiptId(Integer id);
}

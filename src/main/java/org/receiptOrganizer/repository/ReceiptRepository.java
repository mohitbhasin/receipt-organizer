package org.receiptOrganizer.repository;

import org.receiptOrganizer.dao.ReceiptDAO;
import org.springframework.data.repository.ListCrudRepository;

public interface ReceiptRepository extends ListCrudRepository<ReceiptDAO, Integer> {

}

package org.receiptOrganizer.repository;

import org.receiptOrganizer.model.Receipt;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ReceiptRepository extends ListCrudRepository<Receipt, Integer> {

}

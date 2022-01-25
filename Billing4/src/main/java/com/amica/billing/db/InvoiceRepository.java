package com.amica.billing.db;

import com.amica.billing.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.stream.Stream;

public interface InvoiceRepository extends PagingAndSortingRepository<Customer,Integer> {
    public Stream<Customer> streamAllBy();
}

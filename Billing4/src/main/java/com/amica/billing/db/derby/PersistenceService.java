package com.amica.billing.db.derby;

import com.amica.billing.Customer;
import com.amica.billing.Invoice;
import com.amica.billing.db.CachingPersistence;
import com.amica.billing.db.CustomerRepository;
import com.amica.billing.db.InvoiceRepository;
import org.apache.hadoop.hdfs.server.namenode.ha.ReadOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

public class PersistenceService extends CachingPersistence {

    private CustomerRepository customerRepository;
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PlatformTransactionManager txManager;

    public PersistenceService(CustomerRepository customerRepository, InvoiceRepository invoiceRepository) {
        this.customerRepository = customerRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    @PostConstruct
    public void load() {
//        TransactionTemplate template = new TransactionTemplate(txManager);
//        template.executeWithoutResult(status ‐> super.load());
        super.load();
    }

    @Override
    protected Stream<Customer> readCustomers() {
        customerRepository.streamAllBy();
        return readCustomers();
    }

    @Override
    protected Stream<Invoice> readInvoices() {
        invoiceRepository.streamAllBy();
        return readInvoices();
    }

    @Override
    protected void writeCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    protected void writeInvoice(Invoice invoice) {
        //invoiceRepository.save(customers);
    }
}

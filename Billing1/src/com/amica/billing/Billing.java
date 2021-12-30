package com.amica.billing;

import com.amica.billing.parse.Parser;

import java.util.*;

public class Billing {

    private String customerFilename;
    private String invoiceFilename;
    private Parser parser;
    private List<Invoice> invoices = new ArrayList<>();
    private Map<String, Customer> customers = new HashMap<>();

    public List<Invoice> getInvoices() {
        return Collections.unmodifiableList(invoices);
    }

    public Map<String, Customer> getCustomers() {
        return Collections.unmodifiableMap(customers);
    }

    public Billing(String customerFilename, String invoiceFilename) {
        this.customerFilename = customerFilename;
        this.invoiceFilename = invoiceFilename;
    }

    public static void determineParser(String customerFilename, String invoiceFilename) {
        //store and pass the filenames
    }
}

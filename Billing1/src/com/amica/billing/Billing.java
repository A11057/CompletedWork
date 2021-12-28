package com.amica.billing;

import com.amica.billing.parse.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Billing {

    private String customerFilename;
    private String invoiceFilename;
    private Parser parser;
    private List<Invoice> invoices = new ArrayList<>();
    private Map<String, Customer> customers = new HashMap<>();

    public Billing() {
    }
}

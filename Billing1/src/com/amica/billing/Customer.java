package com.amica.billing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

public class Customer {

    private String firstName;
    private String lastName;
    private String fullName;
    private Terms terms;
    private List<Invoice> invoices = new ArrayList<>();

    public Customer(String firstName, String lastName, Terms terms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.terms = terms;
    }

    public String getName() {
        return fullName = getFirstName() + " " + getLastName();
    }

    public void addInvoice(Invoice invoice){
        if (invoice != null){
            invoices.add(invoice);
        }
    }
}

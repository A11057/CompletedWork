package com.amica.billing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor


//we 're not tracking payment here, we're always assuming it's PIF

public class Invoice {

    private int number = 0;
    private int nextNumber;
    private double amount;
    private LocalDate invoiceDate;
    private Optional<LocalDate> paidDate;
    private Customer customer;

    public Invoice(int number, double amount, LocalDate invoiceDate) {
        this.number = number;
        this.nextNumber = ++number;  //this will go in Billing
        this.amount = amount;
        this.invoiceDate = invoiceDate;
    }

//    public Optional<LocalDate> getPaidDate() {
//        return Optional.ofNullable(paidDate);
//    }

    public void paidDate(Optional<LocalDate> paidDate){
        if (paidDate.isPresent()){
            this.paidDate = paidDate;
            amount = 0;
        }
    }

    public void assignInvoice(Customer customer){
        if (customer != null) {
            this.customer = customer;
            customer.addInvoice(this);
        }
    }

    public boolean isPaidInFull(){
        return true;
    }


}


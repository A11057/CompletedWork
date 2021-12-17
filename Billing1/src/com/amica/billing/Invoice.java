package com.amica.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor


//we 're not tracking payment here, we're always assuming it's PIF

public class Invoice {

    @Getter
    private int number;
    private int nextNumber = 0;
    private double amount;
    private LocalDateTime invoiceDate;
    private LocalDate paidDate;
    private Customer customer;

    public Invoice(int number, double amount, LocalDateTime invoiceDate) {
        this.number = ++nextNumber;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
    }

//    public void setupInvoiceDate() {
//        LocalDateTime invoiceDate = LocalDateTime.now();
//        System.out.println("Current date and time " + invoiceDate);
//    }

    public Optional<LocalDate> getPaidDate() {
        return Optional.ofNullable(paidDate);
    }

    public boolean isPaidInFull(){
        return true;
    }


}


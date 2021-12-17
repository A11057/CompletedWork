package com.amica.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
@AllArgsConstructor

public class Customer {

    @Getter
    private String firstName;
    private String lastName;
    private String fullName;
    private Terms terms;

    public Customer(String firstName, String lastName, Terms terms) {
        this.firstName = firstName;
        this.lastName = lastName;
        //this.terms = terms;
    }

    public String getName() {
        return fullName = firstName + " " + lastName;
    }
}

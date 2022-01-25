package com.amica.billing;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(of={"firstName", "lastName"})
@NoArgsConstructor
@Entity

public class Customer {
    private String firstName;
    private String lastName;
    private Terms terms;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    public Customer(String firstName, String lastName, Terms terms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.terms = terms;
    }

    public String getName() {
    	return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
    	return "Customer: " + getName();
    }
}

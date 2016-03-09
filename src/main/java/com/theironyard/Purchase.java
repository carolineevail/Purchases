package com.theironyard;

import javax.persistence.*;

/**
 * Created by Caroline on 3/9/16.
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    int Id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(String date, String creditCard, String cvv, String category, Customer customer) {
        this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }
}

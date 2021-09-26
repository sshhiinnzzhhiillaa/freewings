package com.sshhiinn.freewings.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Collection<Ticket> tickets;

    @Column(name = "SUM")
    private int sum;

    public Order(){}



}

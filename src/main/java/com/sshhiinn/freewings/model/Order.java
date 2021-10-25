package com.sshhiinn.freewings.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "app_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "APP_USER_ID")
    private User appUser;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @Column(name = "SUM")
    private int sum;

    public Order(){}



}

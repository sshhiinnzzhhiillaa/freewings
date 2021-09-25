package com.sshhiinn.freewings.model;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEAT_ID")
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ID")
    private Flight flight;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "IS_TAKEN")
    private boolean isTaken;

    public Seat(){}

    public Seat(long id, Flight flight, int number, boolean isTaken) {
        this.id = id;
        this.flight = flight;
        this.number = number;
        this.isTaken = isTaken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}

package com.sshhiinn.freewings.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FLIGHT_ID")
    private long id;

    @Column(name = "PLANE_NAME")
    private String planeName;

    @Column(name = "NUMBER_OF_SEATS")
    private int numberOfSeats;

    @Column(name = "DATE_OF_FLIGHT")
    private Date dateOfFlight;

    @Column(name = "AIRLINE_NAME")
    private String airlineName;

    @Column(name = "START_CITY")
    private String startCity;

    @Column(name = "FINAL_CITY")
    private String finalCity;

    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER)
    private Collection<Seat> seats;

    public Flight(){}

    public Flight(long id, String planeName, int numberOfSeats, Date dateOfFlight,
                  String airlineName, String startCity, String finalCity, Collection<Seat> seats) {
        this.id = id;
        this.planeName = planeName;
        this.numberOfSeats = numberOfSeats;
        this.dateOfFlight = dateOfFlight;
        this.airlineName = airlineName;
        this.startCity = startCity;
        this.finalCity = finalCity;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Date getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(Date dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<Seat> seats) {
        this.seats = seats;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getFinalCity() {
        return finalCity;
    }

    public void setFinalCity(String finalCity) {
        this.finalCity = finalCity;
    }
}

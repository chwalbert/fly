package com.cloud.fly.content.core.model;

public class FlightSegment {
    private String flightNumber;
    private String depAirport;
    private String depTime;
    private Integer depDay;
    private String arrAirport;
    private String arrTime;
    private Integer arrDay;


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public Integer getDepDay() {
        return depDay;
    }

    public void setDepDay(Integer depDay) {
        this.depDay = depDay;
    }

    public Integer getArrDay() {
        return arrDay;
    }

    public void setArrDay(Integer arrDay) {
        this.arrDay = arrDay;
    }
}

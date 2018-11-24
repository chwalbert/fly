package com.cloud.fly.content.core.model;

public class FlightCollectorContext {
    /**
     * 出发地
     */
    private String departure;
    /**
     * 目的地
     */
    private String arrival;
    /**
     * 出发日期
     */
    private String departureDate;
    /**
     * 回程日期
     */
    private String returnDate;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}

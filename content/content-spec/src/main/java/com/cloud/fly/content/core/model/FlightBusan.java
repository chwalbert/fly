package com.cloud.fly.content.core.model;

public class FlightBusanFare extends FlightRouteBase {

    /**
     * 釜山价格
     */
    private String superSpecialFare;
    private String specialFare;
    private String regularFare;

    public String getSuperSpecialFare() {
        return superSpecialFare;
    }

    public void setSuperSpecialFare(String superSpecialFare) {
        this.superSpecialFare = superSpecialFare;
    }

    public String getSpecialFare() {
        return specialFare;
    }

    public void setSpecialFare(String specialFare) {
        this.specialFare = specialFare;
    }

    public String getRegularFare() {
        return regularFare;
    }

    public void setRegularFare(String regularFare) {
        this.regularFare = regularFare;
    }
}

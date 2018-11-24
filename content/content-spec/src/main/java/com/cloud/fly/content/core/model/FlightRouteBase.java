package com.cloud.fly.content.core.model;

public class FlightRoute {
    private String flightNo;
    private String depTime;
    private String arrTime;
    /**
     * 亚航价格
     */
    private String lowFare;
    private String premiumFlatbed;

    /**
     * 釜山价格
     */
    private String superSpecialFare;
    private String specialFare;
    private String regularFare;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getLowFare() {
        return lowFare;
    }

    public void setLowFare(String lowFare) {
        this.lowFare = lowFare;
    }

    public String getPremiumFlatbed() {
        return premiumFlatbed;
    }

    public void setPremiumFlatbed(String premiumFlatbed) {
        this.premiumFlatbed = premiumFlatbed;
    }

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

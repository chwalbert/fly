package com.cloud.fly.content.core.model;

public class FlightBusan extends FlightRouteBase {

    /**
     * 釜山价格
     */
    private FlightRouteFare superSpecialFare;
    private FlightRouteFare specialFare;
    private FlightRouteFare regularFare;

    public FlightRouteFare getSuperSpecialFare() {
        return superSpecialFare;
    }

    public void setSuperSpecialFare(FlightRouteFare superSpecialFare) {
        this.superSpecialFare = superSpecialFare;
    }

    public FlightRouteFare getSpecialFare() {
        return specialFare;
    }

    public void setSpecialFare(FlightRouteFare specialFare) {
        this.specialFare = specialFare;
    }

    public FlightRouteFare getRegularFare() {
        return regularFare;
    }

    public void setRegularFare(FlightRouteFare regularFare) {
        this.regularFare = regularFare;
    }
}

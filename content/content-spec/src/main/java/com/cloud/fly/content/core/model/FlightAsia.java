package com.cloud.fly.content.core.model;

public class FlightAsia extends FlightRouteBase {
    /**
     * 亚航价格
     */
    private FlightRouteFare lowFare;

    private FlightRouteFare premiumFlatbed;

    public FlightRouteFare getLowFare() {
        return lowFare;
    }

    public void setLowFare(FlightRouteFare lowFare) {
        this.lowFare = lowFare;
    }

    public FlightRouteFare getPremiumFlatbed() {
        return premiumFlatbed;
    }

    public void setPremiumFlatbed(FlightRouteFare premiumFlatbed) {
        this.premiumFlatbed = premiumFlatbed;
    }
}

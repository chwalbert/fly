package com.cloud.fly.content.core.model;

import java.io.Serializable;
import java.util.List;

public class AirResponse implements Serializable {
    private List<FlightRouteBase> asiaFlightRoutes;
    private List<FlightRouteBase> busanFlightRoutes;

    public List<FlightRouteBase> getAsiaFlightRoutes() {
        return asiaFlightRoutes;
    }

    public void setAsiaFlightRoutes(List<FlightRouteBase> asiaFlightRoutes) {
        this.asiaFlightRoutes = asiaFlightRoutes;
    }

    public List<FlightRouteBase> getBusanFlightRoutes() {
        return busanFlightRoutes;
    }

    public void setBusanFlightRoutes(List<FlightRouteBase> busanFlightRoutes) {
        this.busanFlightRoutes = busanFlightRoutes;
    }
}
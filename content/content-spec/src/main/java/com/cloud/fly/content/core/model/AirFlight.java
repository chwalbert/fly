package com.cloud.fly.content.core.model;

import java.io.Serializable;
import java.util.List;

public class AirFlight implements Serializable {
    private List<FlightSegment> segments;

    private FlightPrice low;

    private FlightPrice premium;

    public List<FlightSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<FlightSegment> segments) {
        this.segments = segments;
    }

    public FlightPrice getLow() {
        return low;
    }

    public void setLow(FlightPrice low) {
        this.low = low;
    }

    public FlightPrice getPremium() {
        return premium;
    }

    public void setPremium(FlightPrice premium) {
        this.premium = premium;
    }
}
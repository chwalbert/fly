package com.cloud.fly.content.core.model;

import java.io.Serializable;
import java.util.List;

public class AirInfo implements Serializable {
    private List<AirFlight> fromSegments;

    private List<AirFlight> retSegments;


    public List<AirFlight> getFromSegments() {
        return fromSegments;
    }

    public void setFromSegments(List<AirFlight> fromSegments) {
        this.fromSegments = fromSegments;
    }

    public List<AirFlight> getRetSegments() {
        return retSegments;
    }

    public void setRetSegments(List<AirFlight> retSegments) {
        this.retSegments = retSegments;
    }
}
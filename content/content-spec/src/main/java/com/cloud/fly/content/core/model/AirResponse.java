package com.cloud.fly.content.core.model;

import java.io.Serializable;
import java.util.List;

public class AirResponse implements Serializable {
    private List<AirInfo> fromSegments;

    private List<AirInfo> retSegments;


    public List<AirInfo> getFromSegments() {
        return fromSegments;
    }

    public void setFromSegments(List<AirInfo> fromSegments) {
        this.fromSegments = fromSegments;
    }

    public List<AirInfo> getRetSegments() {
        return retSegments;
    }

    public void setRetSegments(List<AirInfo> retSegments) {
        this.retSegments = retSegments;
    }
}
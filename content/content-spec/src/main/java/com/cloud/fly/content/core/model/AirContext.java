package com.cloud.fly.content.core.model;

import java.util.List;

public class AirContext {

    private String tripType;
    /**
     * 出发地
     */
    private String depCode;
    /**
     * 目的地
     */
    private String arrCode;
    /**
     * 出发日期
     */
    private String depDate;
    /**
     * 回程日期
     */
    private String returnDate;


    /**
     * 返回结果
     */

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


    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getArrCode() {
        return arrCode;
    }

    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
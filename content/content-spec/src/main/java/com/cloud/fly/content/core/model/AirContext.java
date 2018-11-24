package com.cloud.fly.content.core.model;

import com.cloud.fly.content.core.constant.AirType;

public class AirContext {

    private AirType type;
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

    private AirResponse response;

    public AirContext() {
        this.response = new AirResponse();
    }

    public AirType getType() {
        return type;
    }

    public void setType(AirType type) {
        this.type = type;
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

    public AirResponse getResponse() {
        return response;
    }

    public void setResponse(AirResponse response) {
        this.response = response;
    }
}
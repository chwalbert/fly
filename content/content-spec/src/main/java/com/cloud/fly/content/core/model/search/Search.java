package com.cloud.fly.content.core.model.search;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/19 22:50
 * @Description:
 */
public class Search implements Serializable {
    /**
     * 接口身份标识用户名(渠道唯一标识)
     */
    private String userName;
    /**
     * 行程类型， 1:单程; 2:往返;
     */
    private String tripType;
    /**
     * 出发地城市三字码
     */
    private String fromCity;
    /**
     * 出发地城市三字码
     */
    private String toCity;
    /**
     * 去程日期，格式为 YYYYMMDD
     */
    private String fromDate;
    /**
     * 回程日期，格式为 YYYYMMDD(留空表示单程)
     */
    private String retDate;
    /**
     * YSCF 经济/超级经济舱
     * CF 公务舱/头等舱
     * C 公务舱
     * F 头等舱
     * 舱等组合，[Y,S,C,F]、[Y,S]、[C,F]、[C]、[F]
     */
    private List<String> cabinGrade;
    /**
     * 成人人数，1-9
     */
    private Integer adultNum;
    /**
     * 儿童人数，0-9
     */
    private Integer childNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getRetDate() {
        return retDate;
    }

    public void setRetDate(String retDate) {
        this.retDate = retDate;
    }

    public List<String> getCabinGrade() {
        return cabinGrade;
    }

    public void setCabinGrade(List<String> cabinGrade) {
        this.cabinGrade = cabinGrade;
    }

    public Integer getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    @Override
    public String toString() {
        return "Search{" +
                "userName='" + userName + '\'' +
                ", tripType='" + tripType + '\'' +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", retDate='" + retDate + '\'' +
                ", cabinGrade=" + cabinGrade +
                ", adultNum=" + adultNum +
                ", childNum=" + childNum +
                '}';
    }
}

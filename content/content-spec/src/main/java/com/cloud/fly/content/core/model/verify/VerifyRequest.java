package com.cloud.fly.content.core.model.verify;

import com.cloud.fly.content.core.model.search.RoutingElement;

/**
 * @Auther: chw
 * @Date: 2018/12/20 23:21
 * @Description:
 */
public class VerifyRequest {

    /**
     * 接口身份标识用户名(渠道唯一标识)
     */
    private String userName;
    /**
     * 行程类型， 1:单程; 2:往返;
     */
    private String tripType;

    /**
     * 成人人数，1-9
     */
    private Integer adultNum;
    /**
     * 儿童人数，0-9
     */
    private Integer childNum;

    //报价信息， 参考搜索返回结果中的 Routing Elements 只含航班信息，丌含价格信息和规则 信息。
    private RoutingElement routing;

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

    public RoutingElement getRouting() {
        return routing;
    }

    public void setRouting(RoutingElement routing) {
        this.routing = routing;
    }
}

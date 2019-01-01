package com.cloud.fly.content.core.model.order;

import com.cloud.fly.content.core.model.search.RoutingElement;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/20 23:58
 * @Description:
 */
public class Order {

    /**
     * 接口身份标识用户名(渠道唯一标识)
     */
    private String userName;
    /**
     * 行程类型， 1:单程; 2:往返;
     */
    private String tripType;
    /**
     * 会话标识:标记服务接口调用的唯一标识，相应的调用结果中会原值返回。数字或字母，长 度小于 50 个字符,且丌能为空。
     */
    private String sessionId;

    //报价信息，参考搜索返回结果中的 Routing Element
    private RoutingElement routing;

    //乘机人信息，参考下面 的 passenger Element
    private List<PassengerElement> passengers;

    //联系人信息，参考下 面的 contact Element
    private List<ContactElement> contacts;

    //乘机人购买增值扩展 服务信息，参考下面的 PassengerAux Element;仅客人预订 销售类型为一次销售 的增值服务产品时，会 传值。
    private List<PassengerAuxElement> passengerAuxes;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public RoutingElement getRouting() {
        return routing;
    }

    public void setRouting(RoutingElement routing) {
        this.routing = routing;
    }

    public List<PassengerElement> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerElement> passengers) {
        this.passengers = passengers;
    }

    public List<ContactElement> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactElement> contacts) {
        this.contacts = contacts;
    }

    public List<PassengerAuxElement> getPassengerAuxes() {
        return passengerAuxes;
    }

    public void setPassengerAuxes(List<PassengerAuxElement> passengerAuxes) {
        this.passengerAuxes = passengerAuxes;
    }
}

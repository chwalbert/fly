package com.cloud.fly.content.core.model.order;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/21 00:07
 * @Description:
 */
public class PassengerAuxElement {
    //乘机人信息，参考下面 的 AuxPassengerInfo Element
    private AuxPassengerInfoElement auxPassengerInfo;

    //乘机人购买增值服务 信息，参考下面的 AuxSegmentProduct Element
    private List<AuxSegmentProductElement> auxSegmentProducts;


    public AuxPassengerInfoElement getAuxPassengerInfo() {
        return auxPassengerInfo;
    }

    public void setAuxPassengerInfo(AuxPassengerInfoElement auxPassengerInfo) {
        this.auxPassengerInfo = auxPassengerInfo;
    }

    public List<AuxSegmentProductElement> getAuxSegmentProducts() {
        return auxSegmentProducts;
    }

    public void setAuxSegmentProducts(List<AuxSegmentProductElement> auxSegmentProducts) {
        this.auxSegmentProducts = auxSegmentProducts;
    }
}

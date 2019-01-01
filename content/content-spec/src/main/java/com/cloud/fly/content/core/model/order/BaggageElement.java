package com.cloud.fly.content.core.model.order;

/**
 * @Auther: chw
 * @Date: 2019/1/2 00:13
 * @Description:
 */
public class BaggageElement {

    /**
     * 行李件数，1 表示 1PC
     */
    private Integer baggagePieces;

    /**
     * 行李额限重
     * 1.当 isAllWeight=true 时 表示所有件数的总重量
     * 2.当 isAllWeight=false 时 表示单件限重
     */
    private Integer baggageAllowance;

    /**
     * 是否所有行李重量
     */
    private Boolean isAllWeight;

    public Integer getBaggagePieces() {
        return baggagePieces;
    }

    public void setBaggagePieces(Integer baggagePieces) {
        this.baggagePieces = baggagePieces;
    }

    public Integer getBaggageAllowance() {
        return baggageAllowance;
    }

    public void setBaggageAllowance(Integer baggageAllowance) {
        this.baggageAllowance = baggageAllowance;
    }

    public Boolean getAllWeight() {
        return isAllWeight;
    }

    public void setAllWeight(Boolean allWeight) {
        isAllWeight = allWeight;
    }
}

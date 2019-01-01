package com.cloud.fly.content.core.model.search;

/**
 * @Auther: chw
 * @Date: 2018/12/20 00:01
 * @Description:
 */
public class BaggageRuleElement {

    /**
     * 行李件数，1 表示 1PC
     */
    private Integer baggagePieces;
    /**
     * 行李额限重，1 表示单件限重 1KG
     */
    private Integer baggageAllowance;
    /**
     * 航段序号:从 1 至行程总段数的数值
     */
    private Integer segmentNum;

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

    public Integer getSegmentNum() {
        return segmentNum;
    }

    public void setSegmentNum(Integer segmentNum) {
        this.segmentNum = segmentNum;
    }

    @Override
    public String toString() {
        return "BaggageRuleElement{" +
                "baggagePieces=" + baggagePieces +
                ", baggageAllowance=" + baggageAllowance +
                ", segmentNum=" + segmentNum +
                '}';
    }
}

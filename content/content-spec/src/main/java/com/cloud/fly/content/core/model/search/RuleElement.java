package com.cloud.fly.content.core.model.search;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/19 23:28
 * @Description:
 */
public class RuleElement {
    //yes 退票规定，参考下面的 RefundInfo Element
    private List<RefundInfoElement> refundInfos;


    //  yes 改签规定，参考下面的 ChangeInfo Element
    private List<ChangeInfoElement> changeInfos;
    // yes  行李额规定，参考下面的 BaggageInfo Element
    private BaggageInfoElement baggageInfo;

    /**
     * no  备注信息，最大 300 字符
     */
    private String note;

    public List<RefundInfoElement> getRefundInfos() {
        return refundInfos;
    }

    public void setRefundInfos(List<RefundInfoElement> refundInfos) {
        this.refundInfos = refundInfos;
    }

    public List<ChangeInfoElement> getChangeInfos() {
        return changeInfos;
    }

    public void setChangeInfos(List<ChangeInfoElement> changeInfos) {
        this.changeInfos = changeInfos;
    }

    public BaggageInfoElement getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(BaggageInfoElement baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "RuleElement{" +
                "refundInfos=" + refundInfos +
                ", changeInfos=" + changeInfos +
                ", baggageInfo=" + baggageInfo +
                ", note='" + note + '\'' +
                '}';
    }
}

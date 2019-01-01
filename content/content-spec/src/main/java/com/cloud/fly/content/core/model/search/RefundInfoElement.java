package com.cloud.fly.content.core.model.search;

/**
 * @Auther: chw
 * @Date: 2018/12/19 23:31
 * @Description:
 */
public class RefundInfoElement {

    /**
     * yes 退票类型 0:客票全部未使用;1:客票部分使用 (1 仅往返程可使用)
     */
    private int refundType;

    /**
     * yes 退票标识
     * T:丌可退
     * H:有条件退
     * F:免费退 E:退改签规则以航空公司为准;
     */
    private String refundStatus;

    /**
     * no退票金额，当 refundStatus =H,必须赋值; 如果 refundStatus =T/F,则此字段可丌赋 值
     */
    private Integer refundFee;

    /**
     * 退票费币种，当 refundStatus =H，必须 赋值。IATA 标准币种编码,(目前仅限和 Routing 报价币种一致)
     */
    private String currency;

    /**
     * 乘客类型，0 成人/1 儿童
     */
    private Integer passengerType;

    /**
     * 是否允许 NoShow 退票，T:丌可退，E: 按航司客规为准，H:有条件退，F:免 费退
     */
    private String refNoshow;

    /**
     * no NoShow 时限，即起飞前多久算 NoShow; 单位:小时 ;如丌赋值则认为航班起飞 时间算 NoShow 时间节点
     */
    private Integer refNoShowCondition;

    /**
     * NoShow 后退票费用，即算上 NoShow 罚 金后的退票费用;当 NoShow=H，必须 赋值
     */
    private Integer refNoshowFee;

    /**
     * no 退票备注，最长 500 个字符长度
     */
    private String refRemark;

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    public String getRefNoshow() {
        return refNoshow;
    }

    public void setRefNoshow(String refNoshow) {
        this.refNoshow = refNoshow;
    }

    public Integer getRefNoShowCondition() {
        return refNoShowCondition;
    }

    public void setRefNoShowCondition(Integer refNoShowCondition) {
        this.refNoShowCondition = refNoShowCondition;
    }

    public Integer getRefNoshowFee() {
        return refNoshowFee;
    }

    public void setRefNoshowFee(Integer refNoshowFee) {
        this.refNoshowFee = refNoshowFee;
    }

    public String getRefRemark() {
        return refRemark;
    }

    public void setRefRemark(String refRemark) {
        this.refRemark = refRemark;
    }

    @Override
    public String toString() {
        return "RefundInfoElement{" +
                "refundType=" + refundType +
                ", refundStatus='" + refundStatus + '\'' +
                ", refundFee=" + refundFee +
                ", currency='" + currency + '\'' +
                ", passengerType=" + passengerType +
                ", refNoshow='" + refNoshow + '\'' +
                ", refNoShowCondition=" + refNoShowCondition +
                ", refNoshowFee=" + refNoshowFee +
                ", refRemark='" + refRemark + '\'' +
                '}';
    }
}


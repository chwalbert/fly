package com.cloud.fly.content.core.model.search;

/**
 * @Auther: chw
 * @Date: 2018/12/19 23:52
 * @Description:
 */
public class ChangeInfoElement {
    /**
     * 改期类型 0:客票全部未使用;1:客票部分使用 (1 仅往返程可使用)
     */
    private Integer changeType;

    /**
     * 改期标识
     * T:丌可改期
     * H:有条件改期
     * F:免费改期 E:改签规则以航空公司为准;
     */
    private String changeStatus;
    /**
     * 改期金额，当 changeStatus=H 时，必须 赋值;如果 changeStatus =T/F,则此字段改期金额，当 changeStatus=H 时，必须 赋值;如果 changeStatus =T/F,则此字段
     */
    private Integer changeFee;


    /**
     * 改期费币种，当 changesStatus =H，必须
     * 赋值。IATA 标准币种编码, (目前仅限 和 Routing 报价币种一致)
     */
    private String currency;

    /**
     * 乘客类型，0 成人 /1 儿童
     */
    private Integer passengerType;

    /**
     * 是否允许 NoShow 改期，T:丌可改，E: 按航司客规为准，H:有条件改，F:免 费改
     */
    private String chaNoshow;

    /**
     * NoShow 时限，即起飞前多久算 NoShow; 单位:小时 ;如丌赋值则认为航班起飞 时间算 NoShow 时间节点
     */
    private Integer chaNoShowCondition;
    /**
     * NoShow 后改期费用，即算上 NoShow 罚 金后的改期费用;当 chaNoshow =H，必 须赋值
     */
    private Integer chaNoshowFee;

    /**
     * 改期备注，最长 500 个字符长度
     */
    private String chaRemark;

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(Integer changeFee) {
        this.changeFee = changeFee;
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

    public String getChaNoshow() {
        return chaNoshow;
    }

    public void setChaNoshow(String chaNoshow) {
        this.chaNoshow = chaNoshow;
    }

    public Integer getChaNoShowCondition() {
        return chaNoShowCondition;
    }

    public void setChaNoShowCondition(Integer chaNoShowCondition) {
        this.chaNoShowCondition = chaNoShowCondition;
    }

    public Integer getChaNoshowFee() {
        return chaNoshowFee;
    }

    public void setChaNoshowFee(Integer chaNoshowFee) {
        this.chaNoshowFee = chaNoshowFee;
    }

    public String getChaRemark() {
        return chaRemark;
    }

    public void setChaRemark(String chaRemark) {
        this.chaRemark = chaRemark;
    }

    @Override
    public String toString() {
        return "ChangeInfoElement{" +
                "changeType=" + changeType +
                ", changeStatus='" + changeStatus + '\'' +
                ", changeFee=" + changeFee +
                ", currency='" + currency + '\'' +
                ", passengerType=" + passengerType +
                ", chaNoshow='" + chaNoshow + '\'' +
                ", chaNoShowCondition=" + chaNoShowCondition +
                ", chaNoshowFee=" + chaNoshowFee +
                ", chaRemark='" + chaRemark + '\'' +
                '}';
    }
}

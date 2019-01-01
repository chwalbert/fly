package com.cloud.fly.content.core.model.search;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/19 23:14
 * @Description:
 */
public class RoutingElement {
    /**
     * yes 可保存必要信息，之后生单按原值回转。 最大 1000 个字符
     */
    private String data;
    /**
     * yes【公布运价强校验】成人公布价，丌含 税,当 productType=0 时必须返回，此项 同程将做公布运价校验用，其他类型允 许返回默认值 0
     */
    private Integer publishPrice;

    /**
     * yes 成人单价，丌含税【正整数】
     */
    private Integer adultPrice;

    /**
     * yes 成人税费【整数，最小为 0】
     */
    private Integer adultTax;
    /**
     * yes【公布运价强校验】儿童公布价，丌含 税，若无法提供请返回默认值 0
     */
    private Integer childPublishPrice;

    /**
     * yes 儿童公布价，丌含税【成人+儿童时必须 返回】，若无法提供请返回默认值 0
     */
    private Integer childPrice;

    /**
     * yes 儿童税费，【成人+儿童时必须返回】，若 儿童税费，【成人+儿童时必须返回】，若
     */
    private Integer childTax;
    /**
     * yes 报价币种，默认要返回 CNY，表示报价 为人民币币种;若需使用外币币种进行 报价，需提前沟通同程，否则报价将被 过滤!
     */
    private String currency;

    /**
     * yes 乘客国籍类型: 0.表示没有国籍限制， 1.表示仅适用， 2.表示丌适用 若无法提供请返回默认值 0
     */
    private Integer nationalityType;

    /**
     * yes 乘客国籍，可以为空，若输入则为标准 国家二字码，多个用,逗号‘，’分隔， 例 如 CN,US;最大长度 100 个字符。若无 法提供请返回默认值’’。
     */
    private String nationality;

    /**
     * yes 成人适用年龄区间，使用“-”表示“至”， 例如*-12，表示 12 岁及以下;若无表示 无限制，仅支持录入一个年龄段;最大 长度 10 个字符, 暂时丌支持有年龄限制 的行程。若无法提供请返回默认值’’。
     */
    private String adultAgeRestriction;

    /**
     * yes  报销凭证类型: 0 行程单/1 旅行发票,
     */
    private Integer ticketInvoiceType;

    /**
     * no 【公布运价强校验】 订座系统:GDS 使用 IATA 标准 2 字代 码
     * 1E:TravelSky
     * 1A:Amadeus
     * 1B:Abacus
     * 1S:Sabre
     * 1P:WorldSpan
     * 1G:Galileo
     * 航司官网使用 IATA 标准航司 2 字代码标 示，如 MU:东航官网
     * 运价类型为 GDS 公布运价时，此项为必 须项，其他选填
     */
    private String reservationType;

    /**
     * no 公布运价强校验】
     * 运价基础，每航段 1 个，使用“/”分割。 同程做公布运价校验用，productType=0 时此处丌能为空串其他产品类型时允许 返回空串，丌允许返回 null;最大长度 100 个字符
     */
    private String fareBasis;

    /**
     * no 【公布运价强校验】 出票航司，整个行程只能赋一个航司; 如丌赋值会取行程第一航段的 carrier 作 为出票航司，productType=0 将做公布运价校验;
     */
    private String validatingCarrier;

    /**
     * no 出票类型(productType=4 时必传): 1:通知后出票，2:起飞前出票
     */
    private Integer ticketType;


    /**
     * no 出票时限:1-10080(以分钟为单位) (productType=4 时必传)
     */
    private Integer ticketTimeLimit;


    /**
     * yes 最小出行人数【默认要返回 1】
     */
    private Integer minPassengerCount;

    /**
     * yes 最大出行人数【默认要返回 9】
     */
    private Integer maxPassengerCount;


    private  Integer productCode;
    /**
     * yes运价类型:
     * 0: GDS 公布运价 1:GDS 私有运价 2:航司官网产品 3:廉价航司产品 4:特价产品
     * 若无法提供请返回默认值 0
     */
    private Integer productType;

    //规则信息，参考 Rule Element
    private RuleElement rule;

    // 去程航段按顺序返回，数据结构参考下 面的 Segment Element ;如果为多程第一 程、第二程的信息，全输出到此节点。
    private List<SegmentElement> fromSegments;

    //回程航段按顺序返回，数据结构参考下 面的 Segment Element ;如果为多程第一 程、第二程的信息，全输出到此节点。(单 程搜索为空)
    private List<SegmentElement> retSegments;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(Integer publishPrice) {
        this.publishPrice = publishPrice;
    }

    public Integer getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Integer adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Integer getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(Integer adultTax) {
        this.adultTax = adultTax;
    }

    public Integer getChildPublishPrice() {
        return childPublishPrice;
    }

    public void setChildPublishPrice(Integer childPublishPrice) {
        this.childPublishPrice = childPublishPrice;
    }

    public Integer getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Integer childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getChildTax() {
        return childTax;
    }

    public void setChildTax(Integer childTax) {
        this.childTax = childTax;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getNationalityType() {
        return nationalityType;
    }

    public void setNationalityType(Integer nationalityType) {
        this.nationalityType = nationalityType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAdultAgeRestriction() {
        return adultAgeRestriction;
    }

    public void setAdultAgeRestriction(String adultAgeRestriction) {
        this.adultAgeRestriction = adultAgeRestriction;
    }

    public Integer getTicketInvoiceType() {
        return ticketInvoiceType;
    }

    public void setTicketInvoiceType(Integer ticketInvoiceType) {
        this.ticketInvoiceType = ticketInvoiceType;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getValidatingCarrier() {
        return validatingCarrier;
    }

    public void setValidatingCarrier(String validatingCarrier) {
        this.validatingCarrier = validatingCarrier;
    }

    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getTicketTimeLimit() {
        return ticketTimeLimit;
    }

    public void setTicketTimeLimit(Integer ticketTimeLimit) {
        this.ticketTimeLimit = ticketTimeLimit;
    }

    public Integer getMinPassengerCount() {
        return minPassengerCount;
    }

    public void setMinPassengerCount(Integer minPassengerCount) {
        this.minPassengerCount = minPassengerCount;
    }

    public Integer getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(Integer maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public RuleElement getRule() {
        return rule;
    }

    public void setRule(RuleElement rule) {
        this.rule = rule;
    }

    public List<SegmentElement> getFromSegments() {
        return fromSegments;
    }

    public void setFromSegments(List<SegmentElement> fromSegments) {
        this.fromSegments = fromSegments;
    }

    public List<SegmentElement> getRetSegments() {
        return retSegments;
    }

    public void setRetSegments(List<SegmentElement> retSegments) {
        this.retSegments = retSegments;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "RoutingElement{" +
                "data='" + data + '\'' +
                ", publishPrice=" + publishPrice +
                ", adultPrice=" + adultPrice +
                ", adultTax=" + adultTax +
                ", childPublishPrice=" + childPublishPrice +
                ", childPrice=" + childPrice +
                ", childTax=" + childTax +
                ", currency='" + currency + '\'' +
                ", nationalityType=" + nationalityType +
                ", nationality='" + nationality + '\'' +
                ", adultAgeRestriction='" + adultAgeRestriction + '\'' +
                ", ticketInvoiceType=" + ticketInvoiceType +
                ", reservationType='" + reservationType + '\'' +
                ", fareBasis='" + fareBasis + '\'' +
                ", validatingCarrier='" + validatingCarrier + '\'' +
                ", ticketType=" + ticketType +
                ", ticketTimeLimit=" + ticketTimeLimit +
                ", minPassengerCount=" + minPassengerCount +
                ", maxPassengerCount=" + maxPassengerCount +
                ", productType=" + productType +
                ", rule=" + rule +
                ", fromSegments=" + fromSegments +
                ", retSegments=" + retSegments +
                '}';
    }
}

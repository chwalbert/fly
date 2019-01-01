package com.cloud.fly.content.core.model.order;

/**
 * @Auther: chw
 * @Date: 2018/12/21 00:14
 * @Description:
 */
public class ProductItemElement {

    /**
     * 外部 ID，此增值服务报价的唯一标识，后续用于生单；
     * 长度限制 64 个字符
     */
    private String productItemId;

    /**
     * 产品类型 1:行李额
     */
    private Integer productType;
    /**
     * 销售类型 1:一次销售，2:二次销售，3:不限
     * 一次销售是指增值服务和机票同时购买；
     * 二次销售是指买完机票后，再单独购买增值服务
     */
    private Integer saleType;
    /**
     * 结算价
     * 币种：人民币；单位：元。
     */
    private Long basePrice;
    private BaggageElement baggage;

    private  RefundRuleElement refundRule;

    public String getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(String productItemId) {
        this.productItemId = productItemId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public BaggageElement getBaggage() {
        return baggage;
    }

    public void setBaggage(BaggageElement baggage) {
        this.baggage = baggage;
    }

    public RefundRuleElement getRefundRule() {
        return refundRule;
    }

    public void setRefundRule(RefundRuleElement refundRule) {
        this.refundRule = refundRule;
    }
}

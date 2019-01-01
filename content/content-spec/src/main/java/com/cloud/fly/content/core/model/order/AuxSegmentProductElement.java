package com.cloud.fly.content.core.model.order;

/**
 * @Auther: chw
 * @Date: 2018/12/21 00:10
 * @Description:
 */
public class AuxSegmentProductElement {
    /**
     * 航段索引，如 1 表示
     * <p>
     * 第一段行程
     */
    private Integer segmentIndex;

    //增值服务产品描述信 息，参考增值服务查询 返回参数定义的 ProductItem Element

    private  ProductItemElement productItem;

    public Integer getSegmentIndex() {
        return segmentIndex;
    }

    public void setSegmentIndex(Integer segmentIndex) {
        this.segmentIndex = segmentIndex;
    }

    public ProductItemElement getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItemElement productItem) {
        this.productItem = productItem;
    }
}

package com.cloud.fly.content.core.model.order;

import com.cloud.fly.content.core.model.base.Base;
import com.cloud.fly.content.core.model.search.RoutingElement;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/20 23:58
 * @Description:
 */
public class OrderResponse extends Base {

    /**
     * 会话标识：标记服务接口调用的唯一标识，
     * 相应的调用结果中会原值返回。数字或字母，
     * 长度小于 50 个字符，且不能为空。
     */
    private String sessionId;
    /**
     * 三方订单号，最大 100 个字符(待出票订单捞
     * 取时会返回)
     */
    private String orderNo;
    /**
     * PNR 编码，最大 30 个字符
     */
    private String pnrCode;

    /**
     * 报价信息，参考搜索返回结果中的 Routing
     * Elements。
     */
    private RoutingElement routing;

    /**
     * 增值服务预定结果，参考下单请求报文的
     * AuxOrderResult Element ；
     * 1.当下单请求时存在增值服务商品，此节点必
     * 须返回；
     * 2.若不返回平台判定为，增值服务商品下单失
     * 败；
     * 3.一种增值服务商品返回一个结果；一种增值
     * 服务商品返回多个结果，平台判断为增值服
     * 务商品下单失败
     * 4.机票下单失败，平台则自动判定增值服务商
     * 品也下单失败，不再关心增值服务商品下单
     */
    private List<AuxOrderResultElement> auxOrderResult;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public RoutingElement getRouting() {
        return routing;
    }

    public void setRouting(RoutingElement routing) {
        this.routing = routing;
    }

    public List<AuxOrderResultElement> getAuxOrderResult() {
        return auxOrderResult;
    }

    public void setAuxOrderResult(List<AuxOrderResultElement> auxOrderResult) {
        this.auxOrderResult = auxOrderResult;
    }
}

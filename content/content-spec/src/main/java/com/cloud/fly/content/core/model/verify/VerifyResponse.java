package com.cloud.fly.content.core.model.verify;

import com.cloud.fly.content.core.model.base.Base;
import com.cloud.fly.content.core.model.search.RoutingElement;
import com.cloud.fly.content.core.model.search.RuleElement;

/**
 * @Auther: chw
 * @Date: 2018/12/20 23:21
 * @Description:
 */
public class VerifyResponse extends Base {

    /**
     * 会话标识:标记服务接口调用的唯一标识，相应的调用结果中会原值返回。数字或字母，长 度小于 50 个字符,且丌能为空。
     */
    private String sessionId;

    //报价信息，参考搜索返回结果中的 Routing Element
    private RoutingElement routing = new RoutingElement();

    //规则信息，参考搜索返回结果中的 Rule Element
    private RuleElement rule = new RuleElement();

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public RoutingElement getRouting() {
        return routing;
    }

    public void setRouting(RoutingElement routing) {
        this.routing = routing;
    }

    public RuleElement getRule() {
        return rule;
    }

    public void setRule(RuleElement rule) {
        this.rule = rule;
    }
}

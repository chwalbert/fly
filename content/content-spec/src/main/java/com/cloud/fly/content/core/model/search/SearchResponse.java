package com.cloud.fly.content.core.model.search;

import com.cloud.fly.content.core.model.base.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/19 22:50
 * @Description:
 */
public class SearchResponse extends Base {

    //yes 报价信息，参考下面的 Routing Element
    private List<RoutingElement> routings = new ArrayList<>();

    public List<RoutingElement> getRoutings() {
        return routings;
    }

    public void setRoutings(List<RoutingElement> routings) {
        this.routings = routings;
    }

    @Override
    public String toString() {
        return super.toString() + "SearchResponse{" +
                "routings=" + routings +
                '}';
    }
}

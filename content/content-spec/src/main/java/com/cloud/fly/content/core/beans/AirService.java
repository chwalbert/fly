package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.model.AirContext;
import com.cloud.fly.content.core.model.AirResponse;

/**
 * @Auther: chw
 * @Date: 2018/11/19 22:59
 * @Description: 数据爬取
 */
public interface AirService {
    /**
     * 爬取航班信息
     *
     * @param context
     * @return
     */
    String collectorFlight(AirContext context);

    /**
     * 解析航班
     *
     * @param htmlStr
     */
    AirResponse parseFlight(String htmlStr);



}

package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.model.AirContext;

/**
 * @Auther: chw
 * @Date: 2018/11/19 22:59
 * @Description: 数据爬取
 */
public interface AirCollector {
    /**
     * 爬取航司出发目的地
     *
     * @return
     */
    String collectorStation();

    /**
     * 爬取航班信息
     *
     * @param context
     * @return
     */
    String collectorFlight(AirContext context);

}

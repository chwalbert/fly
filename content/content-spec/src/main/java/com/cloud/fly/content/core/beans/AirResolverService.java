package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.model.FlightRouteBase;
import com.cloud.fly.content.core.model.Station;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/11/18 17:24
 * @Description: 航司解析
 */
public interface AirResolverService {


    /**
     * 解析航空站
     *
     * @param htmlStr
     */
    List<Station> parseStation(String htmlStr);

    /**
     * 解析航班
     *
     * @param htmlStr
     */
    List<FlightRouteBase> parseFlight(String htmlStr);

}

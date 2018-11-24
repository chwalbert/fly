package com.cloud.fly.content.core.beans;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.fly.content.core.model.FlightRoute;
import com.cloud.fly.content.core.model.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/11/18 17:24
 * @Description: 釜山
 */
@Service
public class BusanServiceImpl implements AirResolverService {

    private static Logger log = LoggerFactory.getLogger(BusanServiceImpl.class);

    @Override
    public List<Station> parseStation(String htmlStr) {

        List<Station> list = new ArrayList<>();
        JSONObject object = JSON.parseObject(htmlStr);
        JSONArray jsonArray = object.getJSONArray("city");
        Station station = new Station();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            station.setCountryName(jsonObject.getString("abbr"));
            station.setCountryCode(jsonObject.getString("ctlval1") + jsonObject.getString("ctlval2"));
            station.setStationName(jsonObject.getString("descr"));
            station.setStationCode(jsonObject.getString("code"));
//            station.setAirportName(jsonObject.getString(""));
            station.setLatitude(jsonObject.getString("latitude"));
            station.setLongitude(jsonObject.getString("longitude"));
            list.add(station);
        }
        return list;
    }

    @Override
    public List<FlightRoute> parseFlight(String htmlStr) {
        List<FlightRoute> list = new ArrayList<>();
        JSONObject object = JSON.parseObject(htmlStr);
        JSONArray jsonArray = object.getJSONArray("listItineraryFare");

        for (Object obj1 : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) obj1;
            JSONArray objects1 = jsonObject1.getJSONArray("listFlight");
            if (objects1 == null || objects1.isEmpty()) {
                continue;
            }
            for (Object obj2 : objects1) {
                JSONObject jsonObject2 = (JSONObject) obj2;
                FlightRoute route = new FlightRoute();
                route.setFlightNo(jsonObject2.getString("flightNo"));
                route.setDepTime(jsonObject2.getString("depTime"));
                route.setArrTime(jsonObject2.getString("arrTime"));

                JSONArray jsonArray3 = jsonObject2.getJSONArray("listCls");
                if (jsonArray3 == null || jsonArray3.isEmpty()) {
                    continue;
                }

                for (Object obj3 : jsonArray3) {
                    JSONObject jsonObject3 = (JSONObject) obj3;
                    String cls = jsonObject3.getString("cls");
                    if ("A".equals(cls)) {
                        route.setSuperSpecialFare(jsonObject2.getString("priceAd"));
                    } else if ("L".equals(cls)) {
                        route.setSpecialFare(jsonObject2.getString("priceAd"));
                    } else if ("S".equals(cls)) {
                        route.setRegularFare(jsonObject2.getString("priceAd"));
                    }
                }

                list.add(route);

            }

        }

        return list;

    }

}

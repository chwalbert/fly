package com.cloud.fly.content.core.beans;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.fly.content.core.model.FlightRoute;
import com.cloud.fly.content.core.model.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: chw
 * @Date: 2018/11/18 17:24
 * @Description: 亚航
 */
@Service
public class AsiaServiceImpl implements AirResolverService {

    private static Logger log = LoggerFactory.getLogger(AsiaServiceImpl.class);

    @Override
    public List<Station> parseStation(String htmlStr) {
        List<Station> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(htmlStr);
        Station station = new Station();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            station.setCountryName(jsonObject.getString("CountryName"));
            station.setCountryCode(jsonObject.getString("CountryCode"));
            station.setStationName(jsonObject.getString("StationName"));
            station.setStationCode(jsonObject.getString("CountryCode"));
            station.setAirportName(jsonObject.getString("AirportName"));
            station.setLatitude(jsonObject.getString("Lat"));
            station.setLongitude(jsonObject.getString("Long"));
            list.add(station);
        }

        return list;

    }

    @Override
    public List<FlightRoute> parseFlight(String htmlStr) {
        log.info("-------parseFlight entry-------");

        if (StringUtils.isEmpty(htmlStr)) {
            log.error("parseFlight htmlStr  is null.");
            return null;
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(htmlStr);
        } catch (Exception ex) {
            log.error(" parse  htmlStr parse exp. htmlStr=" + htmlStr);
        }

        if (doc == null) {
            log.error(" htmlStr document is null.");
            return null;
        }

        // 获取 票价table
        Element table = doc.select(".table.avail-table").first();
        if (table == null) {
            log.warn(".table.avail-table is null.");
            return null;
        }


        Elements routeEle = table.getElementsByAttributeValueMatching("class", "^fare-[a-z]+-row");

        if (routeEle == null) {
            log.warn("fare-light-row is null.");
            return null;
        }

        List<FlightRoute> list = new ArrayList<>();
        routeEle.forEach(r -> {
            FlightRoute map = getRoute(r);
            if (map != null) {
                list.add(map);
            }
        });
        log.info("-------parseFlight end-------");
        return list;
    }

    private FlightRoute getRoute(Element table) {
        //出发时间
        Elements timeMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-vert avail-fare-td avail-table-top-border-[a-z]");

        if (timeMatching == null || timeMatching.size() <= 0) {
            return null;
        }

        List<String> timeList = new ArrayList<>();

        Element timeElement = timeMatching.first();
        Elements times = timeElement.getElementsByClass("avail-table-detail");
        times.forEach(t -> {
            Elements allElements = t.getElementsByClass("text-center").first().getAllElements();
            List<Node> nodeList = allElements.first().childNodes();
            StringBuilder timeStr = new StringBuilder();
            nodeList.stream()
                    .filter(node -> !CollectionUtils.isEmpty(node.childNodes()))
                    .forEach(node -> {
                        String time = node.childNodes().get(0).toString().trim();
                        timeStr.append(time);
                    });
            timeList.add(timeStr.toString());

        });


        Elements cheapMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-top-border-[a-z\\s-]+LF");
        //低航费
        Element cheapEle = cheapMatching.first();


        String cheapPrice = "";
        if (hasElementsByClass(cheapEle, "avail-fare-price")) {
            cheapPrice = cheapEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();
        }

        String cheapDesc = "";
        if (hasElementsByClass(cheapEle, "avail-fare-promo-desc")) {
            cheapDesc = cheapEle.getElementsByClass("avail-fare-promo-desc").first().childNodes().get(0).toString().trim();
        }
        String cheapRemaining = "";
        if (hasElementsByClass(cheapEle, "avail-table-seats-remaining")) {
            cheapRemaining = cheapEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
        }

        Map<String, String> cheapMap = new HashMap<>();
        cheapMap.put("price", cheapPrice);
        cheapMap.put("remaining", cheapRemaining);
        cheapMap.put("desc", cheapDesc);


        //豪华平躺座椅
        Elements expensiveMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-top-border-[a-z\\s-]+BC");

        Element expensiveEle = expensiveMatching.first();

        String expensivePrice = "";
        if (hasElementsByClass(expensiveEle, "avail-fare-price")) {
            expensivePrice = expensiveEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();
        }

        String expensiveRemaining = "";
        if (hasElementsByClass(expensiveEle, "avail-table-seats-remaining")) {
            expensiveRemaining = expensiveEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
        }


        Map<String, String> expensiveMap = new HashMap<>();
        expensiveMap.put("price", expensivePrice);
        expensiveMap.put("remaining", expensiveRemaining);


        FlightRoute route = new FlightRoute();
        Map map = new HashMap();
        map.put("time", timeList);
        map.put("cheap", cheapMap);
        map.put("expensive", expensiveMap);

        route.setDepTime(CollectionUtils.isEmpty(timeList) ? "" : timeList.get(0));
        route.setArrTime(CollectionUtils.isEmpty(timeList) ? "" : timeList.get(timeList.size() - 1));
        route.setLowFare(cheapPrice);
        route.setPremiumFlatbed(expensivePrice);
        return route;
    }

    private static boolean hasElementsByClass(Element element, String className) {
        if (element != null &&
                element.getElementsByClass(className) != null &&
                element.getElementsByClass(className).size() > 0) {
            return true;
        }
        return false;
    }
}

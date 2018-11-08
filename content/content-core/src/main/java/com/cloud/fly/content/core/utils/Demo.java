package com.cloud.fly.content.core.utils;

import com.alibaba.druid.support.json.JSONUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    private static Logger log = LoggerFactory.getLogger(Demo.class);

    private static final String URL_PRICE = "https://booking.airasia.com/Flight/Select?o1=%s&d1=%s&culture=zh-CN&dd1=%s&ADT=1&s=true&mon=true&cc=CNY&c=false";

    private static final String URL_STATION = "https://sch.apiairasia.com/station/zh-cn/file.json";

    public static List<Map<String, String>> getPrice(String from, String to, String data) throws Exception {
        log.info("getPrice  begin {} {} {}", from, to, data);

        String url = String.format(URL_PRICE, from, to, data);

        log.info("getPrice url {} ", url);

        String htmlStr;
        try {
            htmlStr = UrlCrawler.crawlerHtml(url);
        } catch (Exception exp) {
            log.error("crawler Html exp.url=" + url, exp);
            htmlStr = UrlCrawler.getHtmlCache(url);
        }

        if (StringUtils.isEmpty(htmlStr)) {
            log.error("crawler html  is null.");
            return null;
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(htmlStr);
        } catch (Exception ex) {
            log.error(" Html  jsoup parse exp. htmlStr=" + htmlStr);
        }

        if (doc == null) {
            log.error(" html document is null.");
            return null;
        }

        // 获取 票价table
        Element table = doc.select(".table.avail-table").first();
        if (table == null) {
            log.warn(".table.avail-table is null.");
            return null;
        }
        Elements routeEle = table.getElementsByClass("fare-light-row");

        List<Map<String, String>> list = new ArrayList<>();
        routeEle.forEach(r -> {
            Map map = getRouteMap(table);
            list.add(map);
        });

        UrlCrawler.setHtmlCache(url, htmlStr);

        log.info("getPrice  end");
        return list;
    }

    private static Map getRouteMap(Element table) {
        //出发时间
        Element timeElement = table.select(".avail-table-vert.avail-fare-td.avail-table-top-border-black").first();
        Elements times = timeElement.getElementsByClass("avail-table-detail");

        List<String> timeList = new ArrayList<>();
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

        //低航费
        Element cheapEle = table.select(".avail-table-top-border-black.avail-fare.depart.LF").first();
        String cheapPrice = cheapEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();

        String cheapDesc = "";
        if (cheapEle.getElementsByClass("avail-fare-promo-desc") != null &&
                cheapEle.getElementsByClass("avail-fare-promo-desc").size() > 0) {
            cheapDesc = cheapEle.getElementsByClass("avail-fare-promo-desc").first().childNodes().get(0).toString().trim();
        }
        String cheapRemaining = "";
        if (cheapEle.getElementsByClass("avail-table-seats-remaining") != null
                && cheapEle.getElementsByClass("avail-table-seats-remaining").size() > 0) {
            cheapRemaining = cheapEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
        }

        Map<String, String> cheapMap = new HashMap<>();
        cheapMap.put("price", cheapPrice);
        cheapMap.put("remaining", cheapRemaining);
        cheapMap.put("desc", cheapDesc);


        //豪华平躺座椅
        Element expensiveEle = table.select(".avail-table-top-border-black.avail-fare.depart.BC").first();
        String expensivePrice = expensiveEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();

        String expensiveRemaining = "";
        if (expensiveEle.getElementsByClass("avail-table-seats-remaining") != null &&
                expensiveEle.getElementsByClass("avail-table-seats-remaining").size() > 0) {
            expensiveRemaining = expensiveEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
        }

        Map<String, String> expensiveMap = new HashMap<>();
        expensiveMap.put("price", expensivePrice);
        expensiveMap.put("remaining", expensiveRemaining);

        Map map = new HashMap();
        map.put("time", timeList);
        map.put("cheap", cheapMap);
        map.put("expensive", expensiveMap);
        return map;
    }

    public static Object getStation() {

        String htmlStr = UrlCrawler.getHtmlCache(URL_STATION);


        if (!StringUtils.isEmpty(htmlStr)) {
            return JSONUtils.parse(htmlStr);
        }
        try {
            RestTemplate restTemplate = new RestTemplate();
            Object object = restTemplate.getForObject(URL_STATION, Object.class);

            UrlCrawler.setHtmlCache(URL_STATION, JSONUtils.toJSONString(object));

            return object;
        } catch (Exception exp) {
            log.error("station Html exp.url=" + URL_STATION, exp);
        }
        return null;
    }


    public static void main(String[] args) throws Exception {

//        System.out.println(getPrice("PEK", "SIN", "2018-12-12"));
//        System.out.println(getStation());


    }
}
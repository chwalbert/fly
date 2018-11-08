package com.cloud.fly.content.core.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    private static Logger log = LoggerFactory.getLogger(Demo.class);

    private static final String URL = "https://booking.airasia.com/Flight/Select?o1=%s&d1=%s&culture=zh-CN&dd1=%s&ADT=1&s=true&mon=true&cc=CNY&c=false";

    public static void main(String[] args) throws Exception {
        System.out.println(getPrice("PEK", "SIN", "2018-12-12"));
    }

    public static List<Map<String, String>> getPrice(String from, String to, String data) throws Exception {
        log.info("getPrice  begin {} {} {}", from, to, data);

        String url = String.format(URL, from, to, data);

        log.info("getPrice url {} ", URL);

        String htmlStr = "";
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
        String cheap = cheapEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();
        String cheapDesc = cheapEle.getElementsByClass("avail-fare-promo-desc").first().childNodes().get(0).toString().trim();

        Map<String, String> cheapMap = new HashMap<>();
        cheapMap.put("price", cheap);
        cheapMap.put("desc", cheapDesc);

        //豪华平躺座椅
        Element expensiveEle = table.select(".avail-table-top-border-black.avail-fare.depart.BC").first();
        String expensive = expensiveEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();
        String expensiveDesc = expensiveEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();

        Map<String, String> expensiveMap = new HashMap<>();
        expensiveMap.put("price", expensive);
        expensiveMap.put("desc", expensiveDesc);

        Map map = new HashMap();
        map.put("time", timeList);
        map.put("cheap", cheapMap);
        map.put("expensive", expensiveMap);
        return map;
    }
}
package com.cloud.fly.content.core.utils;

import com.alibaba.druid.support.json.JSONUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        Elements routeEle = table.getElementsByAttributeValueMatching("class", "^fare-[a-z]+-row");

        if (routeEle == null) {
            log.warn("fare-light-row is null.");
            return null;
        }

        List<Map<String, String>> list = new ArrayList<>();
        routeEle.forEach(r -> {
            Map map = getRouteMap(r);
            if (map != null) {
                list.add(map);
            }
        });

        UrlCrawler.setHtmlCache(url, htmlStr);

        log.info("getPrice  end");
        return list;
    }

    private static Map getRouteMap(Element table) {
//        //出发时间
//        Elements timeMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-vert avail-fare-td avail-table-top-border-[a-z]");
//
//        if (timeMatching == null || timeMatching.size() <= 0) {
//            return null;
//        }
//
//        List<String> timeList = new ArrayList<>();
//
//        Element timeElement = timeMatching.first();
//        Elements times = timeElement.getElementsByClass("avail-table-detail");
//        times.forEach(t -> {
//            Elements allElements = t.getElementsByClass("text-center").first().getAllElements();
//            List<Node> nodeList = allElements.first().childNodes();
//            StringBuilder timeStr = new StringBuilder();
//            nodeList.stream()
//                    .filter(node -> !CollectionUtils.isEmpty(node.childNodes()))
//                    .forEach(node -> {
//                        String time = node.childNodes().get(0).toString().trim();
//                        timeStr.append(time);
//                    });
//            timeList.add(timeStr.toString());
//
//        });
//
//
//        Elements cheapMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-top-border-[a-z\\s-]+LF");
//        //低航费
//        Element cheapEle = cheapMatching.first();
//
//
//        String cheapPrice = "";
//        if (hasElementsByClass(cheapEle, "avail-fare-price")) {
//            cheapPrice = cheapEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();
//        }
//
//        String cheapDesc = "";
//        if (hasElementsByClass(cheapEle, "avail-fare-promo-desc")) {
//            cheapDesc = cheapEle.getElementsByClass("avail-fare-promo-desc").first().childNodes().get(0).toString().trim();
//        }
//        String cheapRemaining = "";
//        if (hasElementsByClass(cheapEle, "avail-table-seats-remaining")) {
//            cheapRemaining = cheapEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
//        }
//
//        Map<String, String> cheapMap = new HashMap<>();
//        cheapMap.put("price", cheapPrice);
//        cheapMap.put("remaining", cheapRemaining);
//        cheapMap.put("desc", cheapDesc);
//
//
//        //豪华平躺座椅
//        Elements expensiveMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-top-border-[a-z\\s-]+BC");
//
//        Element expensiveEle = expensiveMatching.first();
//
//        String expensivePrice = "";
//        if (hasElementsByClass(expensiveEle, "avail-fare-price")) {
//            expensivePrice = expensiveEle.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();
//        }
//
//        String expensiveRemaining = "";
//        if (hasElementsByClass(expensiveEle, "avail-table-seats-remaining")) {
//            expensiveRemaining = expensiveEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
//        }
//
//
//        Map<String, String> expensiveMap = new HashMap<>();
//        expensiveMap.put("price", expensivePrice);
//        expensiveMap.put("remaining", expensiveRemaining);


        Map map = new HashMap();
//        map.put("time", timeList);
//        map.put("cheap", cheapMap);
//        map.put("expensive", expensiveMap);
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

    private static boolean hasElementsByClass(Element element, String className) {
        if (element != null &&
                element.getElementsByClass(className) != null &&
                element.getElementsByClass(className).size() > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

//        System.out.println(getPrice("PEK", "SIN", "2018-12-12"));
//        System.out.println(getStation());

        // 要验证的字符串
        String str = "avail-table-top-border-gray  avail-fare depart BC";
        // 邮箱验证规则
        String regEx = "^avail-table-top-border-[a-z\\s-]+BC";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);


    }
}
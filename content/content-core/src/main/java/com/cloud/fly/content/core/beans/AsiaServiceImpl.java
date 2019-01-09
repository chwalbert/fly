package com.cloud.fly.content.core.beans;


import com.cloud.fly.content.core.model.AirContext;
import com.cloud.fly.content.core.model.AirFlight;
import com.cloud.fly.content.core.model.AirInfo;
import com.cloud.fly.content.core.model.FlightPrice;
import com.cloud.fly.content.core.model.FlightSegment;
import com.cloud.fly.content.core.utils.UrlCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: chw
 * @Date: 2018/11/18 17:24
 * @Description: 亚航
 */
@Service
public class AsiaServiceImpl implements AirService {

    //往返                                 https://booking.airasia.com/Flight/Select?o1=AKL&d1=BDO&dd1=2019-01-08&dd2=2019-01-08&r=true&ADT=1&CHD=0&inl=0&s=false&mon=true&cc=CNY
    private static final String return_ = "https://booking.airasia.com/Flight/Select?o1={0}&d1={1}&dd1={2}&dd2={3}&r=true&ADT=1&CHD=2&inl=0&s=false&mon=true&cc=CNY";

    //单程                                  https://booking.airasia.com/Flight/Select?o1=AOR&d1=AKL&dd1=2018-12-25&ADT=1&CHD=0&inl=0&s=false&mon=true&cc=CNY
    private static final String one_way = "https://booking.airasia.com/Flight/Select?o1={0}&d1={1}&dd1={2}&ADT=1&CHD=2&inl=0&s=false&mon=true&cc=CNY";

    private static Logger log = LoggerFactory.getLogger(AsiaServiceImpl.class);

    @Override
    public String collectorFlight(AirContext context) {
//        行程类型，
//        1：单程；
//        2：往返；
        String url;
        if ("1".equals(context.getTripType())) {
            MessageFormat mf = new MessageFormat(one_way);
            url = mf.format(one_way, context.getDepCode(), context.getArrCode(), toDate(context.getDepDate()));

        } else {
            MessageFormat mf = new MessageFormat(return_);
            url = mf.format(return_, context.getDepCode(), context.getArrCode(), toDate(context.getDepDate()), toDate(context.getReturnDate()));
        }
        try {
            String html = UrlCrawler.getHtml(url, true);
            return html;
        } catch (Exception exp) {
            log.error("collector Flight  exp.url=" + url, exp);
        }
        return null;
    }

    @Override
    public AirInfo parseFlight(String htmlStr) {
        log.info("-------parseFlight entry-------");

        Elements elements = checkeElements(htmlStr);

        if (elements == null) {
            return null;
        }

        AirInfo response = new AirInfo();

        for (Element e : elements) {

            Elements elements1 = e.getElementsByClass("avail-header-title");
            if (elements1 == null) {
                log.warn("title tripType is null.");
                continue;
            }
            String tripType = elements1.first().childNodes().get(0).toString();
            if (!"Depart".equals(tripType) && !"Return".equals(tripType)) {
                log.warn("tripType is error." + tripType);
                continue;
            }

            // 获取 票价table
            Element table = e.select(".table.avail-table").first();
            if (table == null) {
                log.warn(".table.avail-table is null.");
                continue;
            }
            Elements routeEle = table.getElementsByAttributeValueMatching("class", "fare-[a-z]+-row");

            if (routeEle == null) {
                log.warn("fare-light-row is null.");
                continue;
            }
            List<AirFlight> infos = new ArrayList<>();
            for (Element r : routeEle) {
                AirFlight info = getRoute(r);
                if (info == null) {
                    continue;
                }
                infos.add(info);
            }

            if ("Depart".equals(tripType)) {
                response.setFromSegments(infos);
            } else if ("Return".equals(tripType)) {
                response.setRetSegments(infos);
            }
        }
        log.info("-------parseFlight end-------");
        return response;
    }

    private Elements checkeElements(String htmlStr) {
        if (StringUtils.isEmpty(htmlStr)) {
            log.error("parseFlight htmlStr  is null.");
            return null;
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(htmlStr);
        } catch (Exception ex) {
            log.error(" parse  htmlStr parse exp.", ex);
        }

        if (doc == null) {
            log.error("jsoup document is null.");
            return null;
        }
        Element form = doc.getElementById("availabilityForm");
        if (form == null) {
            log.warn("availabilityForm is null.");
            return null;
        }
        Elements elements = form.getElementsByClass("js_availability_container");

        if (elements == null) {
            log.warn("js_availability_container is null.");
            return null;
        }
        return elements;
    }

    private AirFlight getRoute(Element table) {

        /**
         *
         * 航段
         */

        if (!hasElementsByClass(table, "avail-table-info")) {
            log.warn("getRoute avail-table-info is null.");
            return null;
        }

        Elements timeMatching = table.getElementsByAttributeValueMatching("class", "^avail-table-vert avail-fare-td avail-table-top-border-[a-z]");

        if (timeMatching == null || timeMatching.size() <= 0) {
            log.warn("^avail-table-vert avail-fare-td avail-table-top-border-[a-z] is null.");
            return null;
        }
        Element timeElement = timeMatching.first();
        Elements segment = timeElement.getElementsByAttributeValueMatching("class", "fare-[a-z]+-row");


        Elements flightNo = timeElement.getElementsByClass("carrier-hover-bold");

        if (segment == null || segment.size() <= 0 || flightNo == null || flightNo.size() <= 0) {
            log.warn("carrier-hover-bold is null.");
            return null;
        }

        List<FlightSegment> segments = new ArrayList<>();
        for (int i = 0; i < segment.size(); i++) {

            Element t = segment.get(i);
            Elements details = t.getElementsByClass("avail-table-detail");

            Element elementCenter0 = details.get(0).getElementsByClass("text-center").first();
            Elements elements0 = elementCenter0.select("div");
            String element0_1 = elements0.get(1).childNodes().get(0).toString();
            String element0_2 = elements0.get(2).childNodes().get(0).toString();
            Integer day0_3 = null;
            if (elements0.size() > 3) {
                String day0_3_str = elements0.get(3).childNodes().get(0).toString();
                day0_3 = Integer.valueOf(replaceDay(day0_3_str));
            }

            String depTime = replaceStr(element0_1);
            String depAirport = replaceAirport(element0_2);

            Element elementCenter1 = details.get(1).getElementsByClass("text-center").first();
            Elements elements1 = elementCenter1.select("div");
            String element1_1 = elements1.get(1).childNodes().get(0).toString();
            String element1_2 = elements1.get(2).childNodes().get(0).toString();
            Integer day1_3 = null;
            if (elements1.size() > 3) {
                String day1_3_srt = elements1.get(3).childNodes().get(0).toString();
                day1_3 = Integer.valueOf(replaceDay(day1_3_srt));
            }

            String arrTime = replaceStr(element1_1);
            String arrAirport = replaceAirport(element1_2);

            String flightNumber = replaceStr(flightNo.get(i).childNodes().get(0).toString());

            FlightSegment flightSegment = new FlightSegment();
            flightSegment.setFlightNumber(flightNumber);
            flightSegment.setDepTime(depTime);
            flightSegment.setDepAirport(depAirport);
            flightSegment.setDepDay(day0_3);
            flightSegment.setArrTime(arrTime);
            flightSegment.setArrAirport(arrAirport);
            flightSegment.setArrDay(day1_3);

            segments.add(flightSegment);
        }

        if (CollectionUtils.isEmpty(segments)) {
            return null;
        }

        /**
         *  低航费
         */
        FlightPrice lowPrice = getPrice(table, "^avail-table-top-border-[a-z\\s-]+LF");

        /**
         * 豪华平躺座椅
         */
        FlightPrice premiumPrice = getPrice(table, "^avail-table-top-border-[a-z\\s-]+BC");


        AirFlight info1 = new AirFlight();
        info1.setSegments(segments);
        info1.setLow(lowPrice);
        info1.setPremium(premiumPrice);

        return info1;
    }

    private FlightPrice getPrice(Element table, String low_class) {
        Elements lowMatching = table.getElementsByAttributeValueMatching("class", low_class);
        Element lowEle = lowMatching.first();
        Elements lowPrice = lowEle.getElementsByClass("avail-fare-price-container");

        String low_adult = "";
        String low_child = "";
        Integer low_seats = null;

        if (lowPrice != null) {
            //价格
            for (Element element : lowPrice) {
                if (hasElementsByClass(element, "avail-fare-pax-type-container") &&
                        hasElementsByClass(element, "avail-fare-price")) {
                    String cheapPriceType = element.getElementsByClass("avail-fare-pax-type-container").first().childNodes().get(0).toString().trim();
                    String cheapPrice = element.getElementsByClass("avail-fare-price").first().childNodes().get(0).toString().trim();

                    if (cheapPriceType.indexOf("Adult") > 0) {
                        low_adult = replacePrice(cheapPrice);
                    } else if (cheapPriceType.indexOf("Child") > 0) {
                        low_child = replacePrice(cheapPrice);
                    }
                }
            }

            //剩余座位
            if (hasElementsByClass(lowEle, "avail-table-seats-remaining")) {
                String seatsStr = lowEle.getElementsByClass("avail-table-seats-remaining").first().childNodes().get(0).toString().trim();
                if (StringUtils.hasText(seatsStr)) {
                    seatsStr = Pattern.compile("[^0-9]").matcher(seatsStr).replaceAll("");
                    low_seats = org.apache.commons.lang3.math.NumberUtils.toInt(seatsStr, 9);
                }
            }
        }

        if (StringUtils.isEmpty(low_adult) && StringUtils.isEmpty(low_child)) {
            return null;
        }
        FlightPrice price = new FlightPrice();
        price.setAdultPrice(low_adult);
        price.setChildPrice(low_child);
        price.setSeats(low_seats);
        return price;
    }

    private static boolean hasElementsByClass(Element element, String className) {
        if (element != null &&
                element.getElementsByClass(className) != null &&
                element.getElementsByClass(className).size() > 0) {
            return true;
        }
        return false;
    }

    private static String replaceAirport(String htmlStr) {
        String str = replaceStr(htmlStr);
        if (str.startsWith("(") && str.endsWith(")")) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    private static String replaceDay(String htmlStr) {
        String str = replaceStr(htmlStr);
        if (str.startsWith("+")) {
            str = str.substring(1, str.length());
        }
        return str;
    }

    private static String replacePrice(String htmlStr) {
        if (htmlStr.startsWith("≈") && htmlStr.endsWith("CNY")) {
            htmlStr = htmlStr.substring(1, htmlStr.length() - 3);
        }
        String str = replaceStr(htmlStr);
        str = str.replaceAll(",", "");
        return str;
    }

    private static String replaceStr(String htmlStr) {
        if (StringUtils.isEmpty(htmlStr)) {
            return "";
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(htmlStr);
        String dest = m.replaceAll("");
        return dest;
    }

    private static String toDate(String date) {

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Date date1 = sdf1.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf2.format(date1);
            return date2;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;


    }

}

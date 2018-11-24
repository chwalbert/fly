package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.model.AirContext;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: chw
 * @Date: 2018/11/19 23:12
 * @Description:
 */
@Service
public class AsiaCollertor implements AirCollector {

    private static final String URL_FLIGHT = "https://booking.airasia.com/Flight/Select?o1=%s&d1=%s&culture=zh-CN&dd1=%s&ADT=1&s=true&mon=true&cc=CNY&c=false";

    private static final String URL_STATION = "https://sch.apiairasia.com/station/zh-cn/file.json";

    private static Logger log = LoggerFactory.getLogger(AsiaCollertor.class);

    @Override
    public String collectorStation() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject(URL_STATION, String.class);
            return object;
        } catch (Exception exp) {
            log.error("station Html exp.url=" + URL_STATION, exp);
        }
        return null;
    }

    @Override
    public String collectorFlight(AirContext context) {
        String url = String.format(URL_FLIGHT, context.getDepCode(), context.getArrCode(), context.getDepDate());
        log.info("collectorFlight {} ", url);
        try {
            String html = Jsoup.connect(url).get().html();
            return html;
        } catch (Exception exp) {
            log.error("collector Flight  exp.url=" + url, exp);
        }
        return null;
    }
}

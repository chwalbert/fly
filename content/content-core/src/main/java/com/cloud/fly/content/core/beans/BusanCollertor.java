package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.model.AirContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: chw
 * @Date: 2018/11/19 23:25
 * @Description:
 */
@Service
public class BusanCollertor implements AirCollector {

    private static Logger log = LoggerFactory.getLogger(BusanCollertor.class);

    private static final String URL_STATION = "https://cn.airbusan.com/web/bookingApi/bookingCity";

    private static final String URL_FLIGHT = "https://cn.airbusan.com/web/bookingApi/flightsAvail";


    @Override
    public String collectorStation() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host", "cn.airbusan.com");
        headers.add("Referer", "https://cn.airbusan.com");
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL_STATION, HttpMethod.GET, new HttpEntity<>(null, headers), String.class);
            return responseEntity.getBody();
        } catch (Exception exp) {
            log.error("station Html exp.url=" + URL_STATION, exp);

        }
        return null;
    }

    @Override
    public String collectorFlight(AirContext context) {


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("bookingCategory", "Individual");
        body.add("focYn", "N");
        body.add("tripType", "OW");
        body.add("depCity1", context.getDepCode());
        body.add("arrCity1", context.getArrCode());
        body.add("depDate1", context.getDepDate());
        body.add("paxCountCorp", "0");
        body.add("paxCountAd", "1");
        body.add("paxCountCh", "0");
        body.add("paxCountIn", "0");


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Host", "cn.airbusan.com");
        headers.add("Referer", "https://cn.airbusan.com");
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL_FLIGHT, HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
            return responseEntity.getBody();
        } catch (Exception exp) {
            log.error("collectorFlight Html exp.url=" + URL_FLIGHT, exp);
        }
        return null;
    }
}

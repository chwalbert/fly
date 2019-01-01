package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.model.AirContext;
import com.cloud.fly.content.core.model.AirInfo;
import com.cloud.fly.content.core.model.AirResponse;
import com.cloud.fly.content.core.model.FlightSegment;
import com.cloud.fly.content.core.model.order.Order;
import com.cloud.fly.content.core.model.order.OrderResponse;
import com.cloud.fly.content.core.model.search.BaggageInfoElement;
import com.cloud.fly.content.core.model.search.ChangeInfoElement;
import com.cloud.fly.content.core.model.search.RefundInfoElement;
import com.cloud.fly.content.core.model.search.RoutingElement;
import com.cloud.fly.content.core.model.search.RuleElement;
import com.cloud.fly.content.core.model.search.Search;
import com.cloud.fly.content.core.model.search.SearchResponse;
import com.cloud.fly.content.core.model.search.SegmentElement;
import com.cloud.fly.content.core.model.verify.Verify;
import com.cloud.fly.content.core.model.verify.VerifyResponse;
import com.cloud.fly.content.core.util.JsonUtil;
import com.cloud.fly.content.data.entity.OrderEntity;
import com.cloud.fly.content.data.mapper.OrderMapper;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: chw
 * @Date: 2018/11/20 23:06
 * @Description:
 */
@Component
public class AirFacade {

    private static Logger log = LoggerFactory.getLogger(AirFacade.class);

    @Autowired
    private AsiaServiceImpl asiaService;

    @Autowired
    private OrderMapper mapper;

    private static Map<String, RoutingElement> ROUTING_DATA_MAP = new HashMap<>();

    private static Map<String, String> SESSION_ID_MAP = new HashMap<>();


    public SearchResponse search(Search search) {
        log.info("search param:", JsonUtil.toJson(search));

        AirContext context = new AirContext();
        context.setTripType(search.getTripType());

        context.setDepCode(search.getFromCity());
        context.setArrCode(search.getToCity());

        context.setDepDate(search.getFromDate());
        context.setReturnDate(search.getRetDate());


        //返回
        SearchResponse searchResponse = new SearchResponse();

        try {

            //数据爬虫
            String html = asiaService.collectorFlight(context);
            AirResponse response = asiaService.parseFlight(html);

            log.info("airResponse:" + JsonUtil.toJson(response));

            boolean result = checkAirResponse(search, response);
            if (!result) {
                searchResponse.setStatus("D100");
                searchResponse.setMsg("no flight");
                return searchResponse;
            }

            //数据构建
            List<RoutingElement> routings = new ArrayList<>();
            for (int i = 0; i < response.getFromSegments().size(); i++) {
                AirInfo airInfo = response.getFromSegments().get(i);
                RoutingElement routingElement = new RoutingElement();
                routingElement.setData(System.currentTimeMillis() + "_" + UUID.randomUUID().toString());
                routingElement.setPublishPrice(0);
                routingElement.setChildPublishPrice(0);
                routingElement.setAdultTax(0);
                routingElement.setChildTax(0);

                if (airInfo.getLow() != null && StringUtils.hasText(airInfo.getLow().getAdultPrice())) {
                    routingElement.setAdultPrice((int) Math.ceil(new Double(airInfo.getLow().getAdultPrice())));
                }

                if (airInfo.getLow() != null && StringUtils.hasText(airInfo.getLow().getChildPrice())) {
                    routingElement.setChildPrice((int) Math.ceil(new Double(airInfo.getLow().getChildPrice())));
                }

                routingElement.setCurrency("CNY");
                routingElement.setNationalityType(0);
                routingElement.setNationality("");
                routingElement.setTicketInvoiceType(0);
//                routingElement.setReservationType();
//                routingElement.setFareBasis();
//                routingElement.setValidatingCarrier();
//                routingElement.setTicketType(1);
//                routingElement.setTicketTimeLimit();
                routingElement.setMinPassengerCount(1);
                routingElement.setMaxPassengerCount(9);
                routingElement.setProductCode(0);
                routingElement.setProductType(3);

                //规则信息，参考
                RuleElement ruleElement = new RuleElement();
                List<RefundInfoElement> refundInfos = new ArrayList();
                RefundInfoElement refundInfoElement = new RefundInfoElement();
                refundInfoElement.setRefundType(0);
                refundInfoElement.setRefundStatus("T");
                refundInfoElement.setCurrency("CNY");
                refundInfoElement.setPassengerType(0);
                refundInfoElement.setRefNoshow("T");
                refundInfos.add(refundInfoElement);

                List<ChangeInfoElement> changeInfos = new ArrayList<>();
                ChangeInfoElement changeInfoElement = new ChangeInfoElement();
                changeInfoElement.setChangeType(0);
                changeInfoElement.setChangeStatus("T");
                changeInfoElement.setPassengerType(0);
                changeInfoElement.setChaNoshow("T");
                changeInfos.add(changeInfoElement);

                BaggageInfoElement baggageInfoElement = new BaggageInfoElement();
                baggageInfoElement.setHasBaggage(0);

                ruleElement.setRefundInfos(refundInfos);
                ruleElement.setChangeInfos(changeInfos);
                ruleElement.setBaggageInfo(baggageInfoElement);
                routingElement.setRule(ruleElement);


                List<SegmentElement> fromSegments = getSegmentElements(airInfo.getSegments());
                routingElement.setFromSegments(fromSegments);

                routings.add(routingElement);

                ROUTING_DATA_MAP.put(routingElement.getData(), routingElement);
            }


            searchResponse.setRoutings(routings);
            searchResponse.setStatus("A0"); //A0 成功
        } catch (Exception exp) {
            log.error("search exp.", exp);
            searchResponse.setStatus("A104");
            searchResponse.setMsg("no flight");
        }
        log.error("search result:", JsonUtil.toJson(searchResponse));
        return searchResponse;
    }


    public VerifyResponse verify(Verify verify) {
        VerifyResponse response = new VerifyResponse();
        response.setSessionId(UUID.randomUUID().toString());
        if (!ROUTING_DATA_MAP.containsKey(verify.getRouting().getData())) {
            response.setStatus("B101");
            response.setMsg("余位不足");
            return response;

        }

        response.setStatus("B0"); //B0 成功
        RoutingElement routingElement = ROUTING_DATA_MAP.get(verify.getRouting().getData());
        response.setRouting(routingElement);
        response.setRule(routingElement.getRule());

        SESSION_ID_MAP.put(response.getSessionId(), verify.getRouting().getData());
        return response;
    }

    public OrderResponse order(Order order) {
        OrderResponse response = new OrderResponse();
        if (!SESSION_ID_MAP.containsKey(order.getSessionId())) {
            response.setStatus("C100");
            response.setMsg("舱位已售完");
            return response;
        }

        String data = SESSION_ID_MAP.get(order.getSessionId());

        RoutingElement element = ROUTING_DATA_MAP.get(data);


        response.setSessionId(order.getSessionId());
        response.setOrderNo(order.getSessionId());
        response.setPnrCode("ABCDEF");
        response.setRouting(element);
        response.setStatus("C0"); //C0 成功

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setSessionId(order.getSessionId());
        orderEntity.setOrderInfo(JsonUtil.toJson(order));
        orderEntity.setRouting(JsonUtil.toJson(element));
        mapper.insert(orderEntity);
        return response;
    }

    private List<SegmentElement> getSegmentElements(List<FlightSegment> flightSegmentList) {
        //去程航段按顺序返回，
        List<SegmentElement> fromSegments = new ArrayList<>();
        for (FlightSegment flightSegment : flightSegmentList) {
            SegmentElement segmentElement = new SegmentElement();
            segmentElement.setCarrier("AXM");
            segmentElement.setFlightNumber(flightSegment.getFlightNumber());
            segmentElement.setDepAirport(flightSegment.getDepAirport());
            segmentElement.setDepTime(DateUtils.formatDate(new Date(), "yyyyMMdd") + flightSegment.getArrTime().replaceAll(":", ""));

            segmentElement.setArrAirport(flightSegment.getArrAirport());
            segmentElement.setArrTime(DateUtils.formatDate(new Date(), "yyyyMMdd") + flightSegment.getArrTime().replaceAll("", ":"));
            segmentElement.setCodeShare(false);
            segmentElement.setCabin("9");
            segmentElement.setCabinCount(9);
            segmentElement.setCabinGrade("Y");
        }
        return fromSegments;
    }

    private boolean checkAirResponse(Search search, AirResponse response) {

        if (search == null || response == null) {
            return false;
        }

        //行程类型， 1:单程; 2:往返;行程类型， 1:单程; 2:往返;
        switch (search.getTripType()) {
            case "1":
                return (!CollectionUtils.isEmpty(response.getFromSegments())
                        && !CollectionUtils.isEmpty(response.getRetSegments()));
            case "2":
                return !CollectionUtils.isEmpty(response.getFromSegments());
            default:
                return false;
        }

    }


}

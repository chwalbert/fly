package com.cloud.fly.content.core.beans;

import com.cloud.fly.content.core.constant.AirType;
import com.cloud.fly.content.core.model.AirContext;
import com.cloud.fly.content.core.model.AirResponse;
import com.cloud.fly.content.core.model.FlightRouteBase;
import com.cloud.fly.content.core.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: chw
 * @Date: 2018/11/20 23:06
 * @Description:
 */
@Component
public class AirFacade {

    @Autowired
    private AsiaResolverServiceImpl asiaResolverService;

    @Autowired
    private BusanResolverServiceImpl busanResolverService;

    @Autowired
    private AsiaCollertor asiaCollertor;

    @Autowired
    private BusanCollertor busanCollertor;


    public AirResponse getFare(AirContext context) {

        final CountDownLatch latch = new CountDownLatch(AirType.values().length);

        AirResponse airResponse = new AirResponse();
        for (AirType airType : AirType.values()) {
            new Thread() {
                public void run() {
                    getFareByType(airType, context);
                    latch.countDown();
                }
            }.start();
        }

        return airResponse;

    }


    public List<Station> getStation(AirContext context) {
        List<Station> stationList = null;
        switch (context.getType()) {
            case asia:
                String asiaStationStr = asiaCollertor.collectorStation();
                stationList = asiaResolverService.parseStation(asiaStationStr);
                break;
            case busan:
                String busanStationStr = busanCollertor.collectorStation();
                stationList = busanResolverService.parseStation(busanStationStr);
                break;
        }

        return stationList;
    }


    public AirContext getFareByType(AirType airType, AirContext context) {
        List<FlightRouteBase> flightRouteBaseList;
        switch (airType) {
            case asia:
                String asiaHtml = asiaCollertor.collectorFlight(context);
                flightRouteBaseList = asiaResolverService.parseFlight(asiaHtml);
                context.getResponse().setAsiaFlightRoutes(flightRouteBaseList);
                break;
            case busan:
                String busanHtml = busanCollertor.collectorFlight(context);
                flightRouteBaseList = busanResolverService.parseFlight(busanHtml);
                context.getResponse().setBusanFlightRoutes(flightRouteBaseList);
                break;
        }
        return context;
    }

}

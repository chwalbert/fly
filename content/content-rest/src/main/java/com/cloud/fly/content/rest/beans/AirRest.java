package com.cloud.fly.content.rest.beans;

import com.cloud.fly.content.core.beans.AirFacade;
import com.cloud.fly.content.core.constant.AirType;
import com.cloud.fly.content.core.model.AirContext;
import com.cloud.fly.content.core.model.AirResponse;
import com.cloud.fly.content.core.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirRest {

    @Autowired
    private AirFacade facade;


    @GetMapping("flight/fare")
    public AirResponse fare(@RequestParam String depCode,
                            @RequestParam String arrCode,
                            @RequestParam(defaultValue = "2018-12-12") String depDate) throws Exception {
        AirContext context = new AirContext();
        context.setDepCode(depCode);
        context.setArrCode(arrCode);
        context.setDepDate(depDate);
        return facade.getFare(context);
    }


    @GetMapping("/flight/station")
    public List<Station> station(@RequestParam AirType type) throws Exception {
        AirContext context = new AirContext();
        context.setType(type);
        return facade.getStation(context);
    }

    @GetMapping("flight/fare/type")
    public AirResponse fare(@RequestParam AirType type,
                            @RequestParam String depCode,
                            @RequestParam String arrCode,
                            @RequestParam(defaultValue = "2018-12-12") String depDate) throws Exception {
        AirContext context = new AirContext();
        context.setType(type);
        context.setDepCode(depCode);
        context.setArrCode(arrCode);
        context.setDepDate(depDate);
        return facade.getFareByType(type, context).getResponse();
    }

}

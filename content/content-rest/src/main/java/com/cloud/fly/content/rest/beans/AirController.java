package com.cloud.fly.content.rest.beans;

import com.cloud.fly.content.core.beans.AirFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AirController {

    @Autowired
    private AirFacade facade;

    @RequestMapping("/")
    public String order(Map<String, Object> model) {
        return "order";
    }


    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("id", id);
        Object obj = facade.orderDetail(id);
        model.put("obj", obj);
        return "detail";
    }

}

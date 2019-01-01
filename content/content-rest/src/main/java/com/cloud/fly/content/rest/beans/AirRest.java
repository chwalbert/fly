package com.cloud.fly.content.rest.beans;

import com.cloud.fly.content.core.beans.AirFacade;
import com.cloud.fly.content.core.model.order.Order;
import com.cloud.fly.content.core.model.search.Search;
import com.cloud.fly.content.core.model.verify.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirRest {

    @Autowired
    private AirFacade facade;


    @PostMapping("/search")
    public Object search(@RequestBody Search search) throws Exception {
        return facade.search(search);
    }

    @PostMapping("/verify")
    public Object verify(@RequestBody Verify verify) throws Exception {
        return facade.verify(verify);
    }

    @PostMapping("/order")
    public Object order(@RequestBody Order order) throws Exception {
        return facade.order(order);
    }


}

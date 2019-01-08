package com.cloud.fly.content.rest.beans;

import com.cloud.fly.content.core.beans.AirFacade;
import com.cloud.fly.content.core.model.order.OrderRequest;
import com.cloud.fly.content.core.model.search.SearchRequest;
import com.cloud.fly.content.core.model.verify.VerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirRest {

    @Autowired
    private AirFacade facade;


    @PostMapping("/search")
    public Object search(@RequestBody SearchRequest search) throws Exception {
        return facade.search(search);
    }

    @PostMapping("/verify")
    public Object verify(@RequestBody VerifyRequest verify) throws Exception {
        return facade.verify(verify);
    }

    @PostMapping("/order")
    public Object order(@RequestBody OrderRequest order) throws Exception {
        return facade.order(order);
    }

    @GetMapping("/admin/order/all")
    public Object orderAll() throws Exception {
        return facade.orderAll();
    }

    @GetMapping("/admin/order/detail")
    public Object orderDetail(@RequestParam Long id) throws Exception {
        return facade.orderDetail(id);
    }

}

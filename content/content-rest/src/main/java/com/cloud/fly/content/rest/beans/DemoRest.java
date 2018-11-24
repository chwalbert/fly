//package com.cloud.fly.content.rest.beans;
//
//import com.cloud.fly.content.core.utils.Demo;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class DemoRest {
//
//    @GetMapping("/demo/price")
//    public Object price(@RequestParam(defaultValue = "PEK") String from,
//                        @RequestParam(defaultValue = "SIN") String to,
//                        @RequestParam(defaultValue = "2018-12-12") String data) throws Exception {
//        return Demo.getPrice(from, to, data);
//    }
//
//    @GetMapping("/demo/station")
//    public Object station() throws Exception {
//        return Demo.getStation();
//    }
//}

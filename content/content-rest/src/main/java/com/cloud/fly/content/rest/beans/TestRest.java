package com.cloud.fly.content.rest.beans;

import com.cloud.fly.content.core.beans.TestService;
import com.cloud.fly.content.core.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {


    @Autowired
    private TestService testService;

    @ResponseBody
    @PostMapping("/test/add")
    public Object addUser(@RequestParam String name) {
        Test testBO = new Test();
        testBO.setName(name);
        return testService.add(testBO);
    }


}

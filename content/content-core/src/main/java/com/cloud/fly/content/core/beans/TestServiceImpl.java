package com.cloud.fly.content.core.beans;


import com.cloud.fly.content.core.model.Test;
import com.cloud.fly.content.data.mapper.TestMapper;
import com.cloud.fly.content.data.entity.TestEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private TestMapper cwHomeBannerDAO;


    @Override
    public Test add(Test add) {

        TestEntity insertDo = new TestEntity();

        BeanUtils.copyProperties(add, insertDo);

        int insert = cwHomeBannerDAO.insert(insertDo);

        if (insert <= 0) {
            throw new RuntimeException("insert  error");
        }

        return add;

    }
}

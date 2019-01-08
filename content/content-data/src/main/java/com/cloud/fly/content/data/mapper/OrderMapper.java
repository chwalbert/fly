package com.cloud.fly.content.data.mapper;

import com.cloud.fly.content.data.entity.OrderEntity;

import java.util.List;


public interface OrderMapper {

    int insert(OrderEntity orderEntity);

    List<OrderEntity> selectAll();

    OrderEntity findById(Long id);

    void updateById(OrderEntity orderEntity);
}
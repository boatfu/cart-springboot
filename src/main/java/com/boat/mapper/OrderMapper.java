package com.boat.mapper;

import com.boat.entity.Order;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

public interface OrderMapper {
    void addOrder(Order order);
    void deleteOrder(int id);
    void updateOrder(Order order);
    List<Order> checkOrders(String email);
}

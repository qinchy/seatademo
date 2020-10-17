package com.qinchy.seatademo.order.api;


import com.qinchy.seatademo.order.api.model.OrderModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface OrderService {

    OrderModel getByUserId(String userId);

    OrderModel addOrder(OrderModel order);
}

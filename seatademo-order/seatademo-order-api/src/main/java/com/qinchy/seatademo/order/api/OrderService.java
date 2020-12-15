package com.qinchy.seatademo.order.api;


import com.qinchy.seatademo.order.api.model.OrderModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface OrderService {

    /**
     * 根据账户编号获取订单
     *
     * @param userId 账户编号
     * @return {@link OrderModel}
     **/
    OrderModel getOrderByUserId(String userId);

    /**
     * 创建订单
     *
     * @param order 订单信息
     * @return {@link OrderModel}
     **/
    OrderModel createOrder(OrderModel order);

    /**
     * 创建订单
     *
     * @param userId 账户信息
     * @param commodityCode 商品编号
     * @param count 商品数量
     * @return {@link OrderModel}
     **/
    OrderModel createOrder(String userId, String commodityCode, Integer count);
}

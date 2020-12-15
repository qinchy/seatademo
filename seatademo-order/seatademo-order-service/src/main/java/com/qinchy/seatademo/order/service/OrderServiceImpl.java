package com.qinchy.seatademo.order.service;


import com.qinchy.seatademo.order.api.OrderService;
import com.qinchy.seatademo.order.api.model.OrderModel;
import com.qinchy.seatademo.order.dao.dataobject.OrderDO;
import com.qinchy.seatademo.order.dao.mapper.OrderMapper;
import com.qinchy.seatademo.order.service.feign.AccountFeignClient;
import com.qinchy.seatademo.order.service.feign.StorageFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 * 订单服务
 *
 * @author qinchy
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final BeanCopier COPIER1 = BeanCopier.create(OrderModel.class, OrderDO.class, false);

    private static final BeanCopier COPIER2 = BeanCopier.create(OrderDO.class, OrderModel.class, false);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private StorageFeignClient storageFeignClient;

    @Override
    public OrderModel getOrderByUserId(String userId) {
        OrderDO orderDO = orderMapper.getOrderByUserId(userId);

        OrderModel orderModel = new OrderModel();
        COPIER2.copy(orderDO, orderModel, null);
        return orderModel;
    }

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        OrderDO orderDO = new OrderDO();
        COPIER1.copy(orderModel, orderDO, null);

        Integer id = orderMapper.createOrder(orderDO);
        orderModel.setId(id);
        return orderModel;
    }

    /**
     * 下单服务(扣账户+减库存)
     *
     * @param userId        账户编号
     * @param commodityCode 商品代码
     * @param count         商品数量
     */
    @Override
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public OrderModel createOrder(String userId, String commodityCode, Integer count) {
        // 新增订单
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        // 减库存
        storageFeignClient.deduct(commodityCode, count);

        // 扣账户
        accountFeignClient.reduce(userId, orderMoney);

        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        orderMapper.createOrder(order);

        OrderModel orderModel = new OrderModel();
        COPIER2.copy(order, orderModel, null);

        return orderModel;
    }

}

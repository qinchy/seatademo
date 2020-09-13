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
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private StorageFeignClient storageFeignClient;

    private static final BeanCopier COPIER1 = BeanCopier.create(OrderModel.class, OrderDO.class, false);

    private static final BeanCopier COPIER2 = BeanCopier.create(OrderDO.class, OrderModel.class, false);

    @Override
    public OrderModel getByUserId(String userId) {
        OrderDO orderDO = orderMapper.getByUserId(userId);

        OrderModel orderModel = new OrderModel();
        COPIER2.copy(orderDO, orderModel, null);
        return orderModel;
    }

    @Override
    public OrderModel addOrder(OrderModel orderModel) {
        OrderDO orderDO = new OrderDO();
        COPIER1.copy(orderModel, orderDO, null);

        Long id = orderMapper.addOrder(orderDO);
        orderModel.setId(id);
        return orderModel;
    }

    /**
     * 下单：创建订单、减库存，涉及到两个服务
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @Override
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        // 新增订单
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        orderMapper.addOrder(order);

        // 减库存
        storageFeignClient.deduct(commodityCode, count);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(String userId, String commodityCode, Integer count) {

        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        orderMapper.addOrder(order);

        accountFeignClient.reduce(userId, orderMoney);

    }
}

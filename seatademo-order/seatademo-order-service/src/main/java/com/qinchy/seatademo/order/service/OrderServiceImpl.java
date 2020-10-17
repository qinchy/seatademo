package com.qinchy.seatademo.order.service;


import com.qinchy.seatademo.order.api.OrderService;
import com.qinchy.seatademo.order.api.model.OrderModel;
import com.qinchy.seatademo.order.dao.dataobject.OrderDO;
import com.qinchy.seatademo.order.dao.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

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
}

package com.qinchy.seatademo.order.dao.mapper;

import com.qinchy.seatademo.order.dao.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Mapper
public interface OrderMapper {

    OrderDO getByUserId(String userId);

    Long addOrder(OrderDO order);
}

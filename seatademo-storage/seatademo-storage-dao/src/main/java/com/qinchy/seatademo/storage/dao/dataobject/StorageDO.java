package com.qinchy.seatademo.storage.dao.dataobject;

import lombok.Data;

/**
 * 库存数据库对象
 *
 * @author qinchy
 */
@Data
public class StorageDO {

    /**
     * 库存编号
     **/
    private Long id;

    /**
     * 商品代码
     **/
    private String commodityCode;

    /**
     * 商品数量
     **/
    private Integer count;
}

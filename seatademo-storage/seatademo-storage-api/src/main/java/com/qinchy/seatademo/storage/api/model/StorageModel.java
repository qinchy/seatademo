package com.qinchy.seatademo.storage.api.model;

import lombok.Data;

/**
 * 库存模型
 *
 * @author qinchy
 */
@Data
public class StorageModel {

    /**
     * 库存编号
     **/
    private Long id;

    /**
     * 商品编号
     **/
    private String commodityCode;

    /**
     * 商品数量
     **/
    private Integer count;

}
package com.qinchy.seatademo.storage.api;

import com.qinchy.seatademo.storage.api.model.StorageModel;

/**
 * 库存服务类
 *
 * @author qinchy
 */
public interface StorageService {

    /**
     * 根据商品编号获取库存
     *
     * @param commodityCode 商品编号
     * @return {@link StorageModel}
     **/
    StorageModel getByCommodityCode(String commodityCode);

    /**
     * 商品上架
     *
     * @param storageModel 库存实体
     * @return {@link StorageModel}
     **/
    StorageModel shelf(StorageModel storageModel);

    /**
     * 扣减库存
     *
     * @param storageModel 库存实体
     * @return {@link int}
     **/
    Boolean deduct(StorageModel storageModel);
}

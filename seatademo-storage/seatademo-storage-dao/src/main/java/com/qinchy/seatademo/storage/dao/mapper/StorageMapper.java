package com.qinchy.seatademo.storage.dao.mapper;

import com.qinchy.seatademo.storage.dao.dataobject.StorageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Mapper
public interface StorageMapper {

    /**
     * 通过商品编号获取库存信息
     * @param commodityCode 商品编号
     * @return {@link StorageDO}
     */
    StorageDO getStorageByCommodityCode(String commodityCode);

    /**
     * 商品上架
     * @param storageDO 库存实体
     * @return {@link StorageDO}
     */
    Long addStorage(StorageDO storageDO);

    /**
     * 扣减库存
     *
     * @param storageDO TODO
     * @return {@link int}
     **/
    int deduct(StorageDO storageDO);
}

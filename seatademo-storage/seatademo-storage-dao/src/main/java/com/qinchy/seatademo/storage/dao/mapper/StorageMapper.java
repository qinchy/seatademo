package com.qinchy.seatademo.storage.dao.mapper;

import com.qinchy.seatademo.storage.dao.dataobject.StorageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Mapper
public interface StorageMapper {

    StorageDO getByCommodityCode(String commodityCode);

    Long addStorage(StorageDO user);
}

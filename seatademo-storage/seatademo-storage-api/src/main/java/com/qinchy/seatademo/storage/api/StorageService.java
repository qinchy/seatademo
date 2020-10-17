package com.qinchy.seatademo.storage.api;

import com.qinchy.seatademo.storage.api.model.StorageModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface StorageService {

    StorageModel getByCommodityCode(String commodityCode);

    StorageModel addStorage(StorageModel user);
}

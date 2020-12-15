package com.qinchy.seatademo.storage.service;


import com.qinchy.seatademo.storage.api.StorageService;
import com.qinchy.seatademo.storage.api.model.StorageModel;
import com.qinchy.seatademo.storage.dao.dataobject.StorageDO;
import com.qinchy.seatademo.storage.dao.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * 库存服务
 *
 * @author qinchy
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final BeanCopier COPIER1 = BeanCopier.create(StorageModel.class, StorageDO.class, false);

    private static final BeanCopier COPIER2 = BeanCopier.create(StorageDO.class, StorageModel.class, false);

    @Autowired
    private StorageMapper storageMapper;

    /**
     * 通过商品代码获取库存信息
     *
     * @param commodityCode 商品代码
     * @return {@link StorageModel}
     **/
    @Override
    public StorageModel getByCommodityCode(String commodityCode) {
        StorageDO storageDO = storageMapper.getStorageByCommodityCode(commodityCode);

        StorageModel storageModel = new StorageModel();
        COPIER2.copy(storageDO, storageModel, null);
        return storageModel;
    }

    /**
     * 商品上架
     *
     * @param storageModel 库存
     * @return {@link StorageModel}
     **/
    @Override
    public StorageModel shelf(StorageModel storageModel) {
        StorageDO storageDO = new StorageDO();
        COPIER1.copy(storageModel, storageDO, null);

        Long id = storageMapper.addStorage(storageDO);
        storageModel.setId(id);
        return storageModel;
    }

    /**
     * 扣减库存
     *
     * @param storageModel 库存实体
     * @return {@link int}
     **/
    @Override
    public Boolean deduct(StorageModel storageModel) {
        StorageDO storageDO = new StorageDO();
        storageDO.setCommodityCode(storageModel.getCommodityCode());
        storageDO.setCount(storageModel.getCount());
        int deduct = storageMapper.deduct(storageDO);
        if (deduct == 1) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}

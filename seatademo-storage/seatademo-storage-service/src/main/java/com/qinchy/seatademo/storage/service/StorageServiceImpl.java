package com.qinchy.seatademo.storage.service;


import com.qinchy.seatademo.storage.dao.dataobject.StorageDO;
import com.qinchy.seatademo.storage.api.StorageService;
import com.qinchy.seatademo.storage.api.model.StorageModel;
import com.qinchy.seatademo.storage.dao.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    private static final BeanCopier COPIER1 = BeanCopier.create(StorageModel.class, StorageDO.class, false);

    private static final BeanCopier COPIER2 = BeanCopier.create(StorageDO.class, StorageModel.class, false);

    @Override
    public StorageModel getByCommodityCode(String commodityCode) {
        StorageDO storageDO = storageMapper.getByCommodityCode(commodityCode);

        StorageModel storageModel = new StorageModel();
        COPIER2.copy(storageDO, storageModel, null);
        return storageModel;
    }

    @Override
    public StorageModel addStorage(StorageModel storageModel) {
        StorageDO storageDO = new StorageDO();
        COPIER1.copy(storageModel, storageDO, null);

        Long id = storageMapper.addStorage(storageDO);
        storageModel.setId(id);
        return storageModel;
    }
}

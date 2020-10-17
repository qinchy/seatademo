package com.qinchy.seatademo.storage.web.controller;

import com.qinchy.seatademo.storage.api.StorageService;
import com.qinchy.seatademo.storage.api.model.StorageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/getStorageByCommodityCode")
    public StorageModel getStorageByCommodityCode(@RequestParam("commodityCode") String commodityCode) {
        return storageService.getByCommodityCode(commodityCode);
    }

    @RequestMapping("/addStorage")
    @ResponseBody
    public StorageModel addStorage(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count) {
        StorageModel storageModel = new StorageModel();
        storageModel.setCommodityCode(commodityCode);
        storageModel.setCount(count);
        return storageService.addStorage(storageModel);
    }
}

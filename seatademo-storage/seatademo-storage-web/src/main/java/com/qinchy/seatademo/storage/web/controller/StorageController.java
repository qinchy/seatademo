package com.qinchy.seatademo.storage.web.controller;

import com.qinchy.seatademo.storage.api.StorageService;
import com.qinchy.seatademo.storage.api.model.StorageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 库存服务controller
 *
 * @author qinchy
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/getStorageByCommodityCode")
    public StorageModel getStorageByCommodityCode(@RequestParam("commodityCode") String commodityCode) {
        return storageService.getByCommodityCode(commodityCode);
    }

    @RequestMapping("/shelf")
    @ResponseBody
    public StorageModel addStorage(@RequestParam("commodityCode") String commodityCode,
                                   @RequestParam("count") Integer count) {
        StorageModel storageModel = new StorageModel();
        storageModel.setCommodityCode(commodityCode);
        storageModel.setCount(count);
        return storageService.shelf(storageModel);
    }

    @RequestMapping("/deduct/{commodityCode}/{orderCount}")
    public Boolean storage(@PathVariable String commodityCode, @PathVariable("orderCount") Integer count) {
        StorageModel storageModel = new StorageModel();
        storageModel.setCommodityCode(commodityCode);
        storageModel.setCount(count);
        return storageService.deduct(storageModel);
    }

    /**
     * 减库存
     *
     * @param commodityCode 商品代码
     * @param count         数量
     * @return
     */
    @RequestMapping(path = "/deduct")
    public Boolean deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count) {
        StorageModel storageModel = new StorageModel();
        storageModel.setCommodityCode(commodityCode);
        storageModel.setCount(count);
        return storageService.deduct(storageModel);
    }
}

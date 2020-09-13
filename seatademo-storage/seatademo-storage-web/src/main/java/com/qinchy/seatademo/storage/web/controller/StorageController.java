package com.qinchy.seatademo.storage.web.controller;

import com.qinchy.seatademo.storage.api.StorageService;
import com.qinchy.seatademo.storage.api.model.StorageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
@RequestMapping("storage")
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

    @RequestMapping("/storage/{commodityCode}/{orderCount}")
    @ResponseBody
    public String storage(@PathVariable String commodityCode, @PathVariable("orderCount") Integer count) {
        StorageModel storageModel = new StorageModel();
        storageModel.setCommodityCode(commodityCode);
        storageModel.setCount(count);
        int result = storageService.deduct(storageModel);
        if (result > 0) {
            return "SUCCESS";
        }
        return "FAIL";
    }

    /**
     * 减库存
     * @param commodityCode 商品代码
     * @param count 数量
     * @return
     */
    @RequestMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        StorageModel storageModel = new StorageModel();
        storageModel.setCommodityCode(commodityCode);
        storageModel.setCount(count);
        storageService.deduct(storageModel);
        return true;
    }
}

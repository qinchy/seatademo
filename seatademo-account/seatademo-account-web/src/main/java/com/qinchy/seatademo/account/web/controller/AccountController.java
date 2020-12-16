/*
 * Copyright (C) 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qinchy.seatademo.account.web.controller;

import com.qinchy.seatademo.account.api.AccountService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 账户服务controller
 *
 * @author qinchy
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Value("${logging.level.io.seata}")
    private String seataLogLevel;

    /**
     * 模拟故障的随机数
     **/
    private Random random = new Random();

    /**
     * 账户服务类
     **/
    @Autowired
    private AccountService accountService;

    /**
     * 扣减账户
     *
     * @param userId 账户编号
     * @param money  账户金额
     * @return {@link String}
     **/
    @PostMapping(value = "/reduce", produces = "application/json")
    public Boolean reduce1(String userId, BigDecimal money) {
        LOGGER.info("Account Service ... xid: " + RootContext.getXID());

        if (random.nextBoolean()) {
            throw new RuntimeException("this is a mock Exception");
        }

        int result = accountService.reduce(userId, money);
        LOGGER.info("Account Service End ... ");
        if (result == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 扣减账户
     *
     * @param userId 账户编号
     * @param money  扣减金额
     * @return {@link Boolean}
     **/
    @GetMapping(value = "/reduce")
    public Boolean reduce2(@RequestParam("userId") String userId, @RequestParam("money") BigDecimal money) {
        LOGGER.info("Account Service ... xid: " + RootContext.getXID());

        if (random.nextBoolean()) {
            throw new RuntimeException("this is a mock Exception");
        }

        int result = accountService.reduce(userId, money);
        LOGGER.info("Account Service End ... ");
        if (result == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @GetMapping("/seataLogLevel")
    public String seataLogLevel(){
        return seataLogLevel;
    }

}

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
package com.qinchy.seatademo.user.web.controller;

import com.qinchy.seatademo.user.api.UserService;
import com.qinchy.seatademo.user.api.model.UserModel;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 用户controller
 *
 * @author qinchy
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private Random random = new Random();

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addAge", produces = "application/json")
    public Boolean user(Long id) {
        LOGGER.info("user Service ... xid: " + RootContext.getXID());

        if (random.nextBoolean()) {
            throw new RuntimeException("this is a mock Exception");
        }

        return userService.addAge(id);
    }

    @PostMapping(value = "/getUserByName", produces = "application/json")
    public UserModel getUserByName(String name) {
        LOGGER.info("user Service ... xid: " + RootContext.getXID());
        return userService.getUserByName(name);
    }

    @PostMapping(value = "/getUserById/{id}")
    public UserModel getUserById(@PathVariable("id") Long id) {
        LOGGER.info("user Service ... xid: " + RootContext.getXID());
        return userService.getUserById(id);
    }

    @PostMapping(value = "/addUser", produces = "application/json")
    public UserModel addUser(UserModel userModel) {
        LOGGER.info("user Service ... xid: " + RootContext.getXID());
        return userService.addUser(userModel);
    }

}

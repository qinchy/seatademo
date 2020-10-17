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

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author xiaojing
 */
@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private final JdbcTemplate jdbcTemplate;
	private Random random;

	public UserController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.random = new Random();
	}

	@PostMapping(value = "/user", produces = "application/json")
	public String user(Long id) {
		LOGGER.info("user Service ... xid: " + RootContext.getXID());

		if (random.nextBoolean()) {
			throw new RuntimeException("this is a mock Exception");
		}

		int result = jdbcTemplate.update(
				"update users set age = age + 1 where id = ?",
				new Object[] { id });
		LOGGER.info("user Service End ... ");
		if (result == 1) {
			return SUCCESS;
		}
		return FAIL;
	}

}

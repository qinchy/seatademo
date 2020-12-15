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

package com.qinchy.seatademo.seata.controller;

import com.qinchy.seatademo.seata.configuration.OrderFeignClient;
import com.qinchy.seatademo.seata.configuration.StorageFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaojing
 */
@RestController
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";
	private static final String USER_ID = "U100001";
	private static final String COMMODITY_CODE = "C00321";
	private static final int ORDER_COUNT = 2;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderFeignClient orderFeignClient;

	@Autowired
	private StorageFeignClient storageFeignClient;

	@GlobalTransactional(timeoutMills = 300000, name = "seatademo-start-service-group")
	@GetMapping(value = "/seata/rest", produces = "application/json")
	public String rest() {

		Boolean result = restTemplate.getForObject(
				"http://127.0.0.1:18082/storage/deduct/" + COMMODITY_CODE + "/" + ORDER_COUNT,
				Boolean.class);

		if (!result) {
			throw new RuntimeException();
		}

		String url = "http://127.0.0.1:18083/order/create";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("userId", USER_ID);
		map.add("commodityCode", COMMODITY_CODE);
		map.add("orderCount", ORDER_COUNT + "");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
				map, headers);

		ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request,
				Boolean.class);

		result = response.getBody();

		if (!result) {
			throw new RuntimeException();
		}

		return SUCCESS;
	}

	@GlobalTransactional(timeoutMills = 300000, name = "seatademo-start-service-group")
	@GetMapping(value = "/seata/feign", produces = "application/json")
	public String feign() {

		Boolean result = storageFeignClient.deduct(COMMODITY_CODE, ORDER_COUNT);

		if (result) {
			throw new RuntimeException();
		}

		result = orderFeignClient.create(USER_ID, COMMODITY_CODE, ORDER_COUNT);

		if (!SUCCESS.equals(result)) {
			throw new RuntimeException();
		}

		return SUCCESS;

	}

}

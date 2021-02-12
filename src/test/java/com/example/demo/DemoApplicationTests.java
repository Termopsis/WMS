package com.example.demo;

import com.example.demo.entity.mq.MQTask;
import com.example.demo.service.mqService.MQTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void WhenApplicationSettingsIsNotSetThenFalse(){

		MQTaskService mqTaskService = new MQTaskService();
		try {
			mqTaskService.executeTask(new MQTask());
		}catch (NullPointerException e){
			e.printStackTrace();
		}

	}

	@Test
	void WhenApplicationSettingsIsSetThenTrue(){
		DemoApplication.pool = new HashMap<>();
		DemoApplication.pool.put("One",Executors.newFixedThreadPool(2));
		DemoApplication.sessionTask = new ConcurrentHashMap<>();

		MQTaskService mqTaskService = new MQTaskService();

		MQTask mqTask = new MQTask();
		mqTask.setType("One");
		mqTask.setValue(1);

		mqTaskService.executeTask(mqTask);
	}
}

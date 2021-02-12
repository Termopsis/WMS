package com.example.demo.controller;

import com.example.demo.entity.mq.MQTask;
import com.example.demo.service.mqService.MQTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SomeEndPoint")
public class MQ_Controller {

    private MQTaskService mqTaskService;

    public MQ_Controller(MQTaskService mqTaskService) {
        this.mqTaskService = mqTaskService;
    }

    @PostMapping("/TheOne")
    public ResponseEntity parseRequestAndPutIntoQueue(@RequestBody MQTask mqTask) {
        mqTaskService.executeTask(mqTask);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

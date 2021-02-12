package com.example.demo.service.mqService;

import com.example.demo.DemoApplication;
import com.example.demo.entity.mq.MQTask;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

@Service
@NoArgsConstructor
public class MQTaskService {

    public void executeTask(MQTask mqTask){

        Map<String, ExecutorService> pool = DemoApplication.pool;
        ConcurrentMap<String, ConcurrentMap<Integer, CompletableFuture<Void>>> sessionTask = DemoApplication.sessionTask;

        ConcurrentMap<Integer, CompletableFuture<Void>> concurrentMap = sessionTask.get(mqTask.getType());
        ExecutorService executorService = pool.get(mqTask.getType());

        if (concurrentMap != null && concurrentMap.containsKey(mqTask.getValue())) {
            concurrentMap.compute(mqTask.getValue(), (i, c) -> c.thenRunAsync(mqTask, executorService));
        } else {
            concurrentMap = new ConcurrentHashMap<>();
            concurrentMap.put(mqTask.getValue(), CompletableFuture.runAsync(mqTask, executorService));
            DemoApplication.sessionTask.put(mqTask.getType(), concurrentMap);
        }
    }
}

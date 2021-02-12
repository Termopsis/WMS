package com.example.demo.entity.mq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MQTask implements Runnable{

    private String type;
    private int value;

    @Override
    public void run() {

        System.out.println("Start task type \""+type+"\" and value \""+value+"\"");

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println("The task was interrupted");
        }

        System.out.println("The task \""+type+"\" and value \""+value+"\" is done");

    }
}

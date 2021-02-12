package com.example.demo.entity.mq;

public class MQTask implements Runnable{

    private String type;
    private int value;

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void run() {

        System.out.println("Start task type \""+type+"\" and value \""+value+"\"");

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println("The task was interrupted");
        }

        System.out.println("The task \""+type+"\" and value \""+value+"\"+ is done");

    }
}

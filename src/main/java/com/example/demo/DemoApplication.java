package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootApplication
public class DemoApplication {

	//Список текущих пулов по Типу
	public static Map<String, ExecutorService> pool = new HashMap<>();

	//Текущие задачи по значению
	public static ConcurrentMap<String, ConcurrentMap<Integer, CompletableFuture<Void>>> sessionTask = new ConcurrentHashMap<>();

	public static void main(String[] args) {

		System.out.println("Start application...");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] arrayOfType = {""};

		//Expect string like as Type1 Type2...
		try {
			System.out.println("Enter request type separates by space...");
			String string = reader.readLine();
			arrayOfType = string.split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String s : arrayOfType) {
			try {
				System.out.println("Enter the number of threads for request with type \"" + s + "\"");
				String string = reader.readLine();
				int number = Integer.valueOf(string);
				pool.put(s, Executors.newFixedThreadPool(number));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Application is running!");
		System.out.println("Service is available at the address \"localhost:8080/SomeEndPoint/TheOne\"\n");
	}
}

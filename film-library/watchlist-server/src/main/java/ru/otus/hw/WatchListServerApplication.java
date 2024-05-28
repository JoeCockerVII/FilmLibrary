package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class WatchListServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchListServerApplication.class, args);
		System.out.printf("WatchList Server Application address: %n%s%n", "http://localhost:8090/");
		System.out.printf("WatchList Server Application address: %n%s%n", "http://localhost:8090/film/list");
		System.out.printf("WatchList Server Application address: %n%s%n", "http://localhost:8090/watch/create");
	}

}

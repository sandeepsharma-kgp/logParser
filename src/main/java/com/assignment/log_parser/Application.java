package com.assignment.log_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		LRUCache lruCache = new LRUCache(5);
		lruCache.insert(1,2);
		lruCache.insert(2,2);
		lruCache.insert(4,2);
		lruCache.insert(3,2);
		lruCache.insert(11,2);
		lruCache.show();
		lruCache.getByKey(2);
		lruCache.insert(6,2);
		lruCache.insert(7,2);
		lruCache.show();
	}

}

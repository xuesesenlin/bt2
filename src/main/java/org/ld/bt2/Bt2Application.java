package org.ld.bt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//springboot
@SpringBootApplication
//缓存
@EnableCaching
//定时器
@EnableScheduling
public class Bt2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bt2Application.class, args);
    }
}

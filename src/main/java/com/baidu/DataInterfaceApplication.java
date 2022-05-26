package com.baidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.baidu.mapper"})
public class DataInterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataInterfaceApplication.class, args);
    }
}

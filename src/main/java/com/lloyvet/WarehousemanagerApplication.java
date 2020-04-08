package com.lloyvet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages={"com.lloyvet.system.mapper"})
public class WarehousemanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehousemanagerApplication.class, args);
    }

}

package com.lloyvet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages={"com.lloyvet.system.mapper","com.lloyvet.business.mapper"})
@EnableCaching
public class WarehousemanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehousemanagerApplication.class, args);
    }

}

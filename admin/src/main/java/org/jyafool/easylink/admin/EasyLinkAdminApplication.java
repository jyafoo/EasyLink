package org.jyafool.easylink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("org.jyafool.easylink.admin.remote")
@MapperScan("org.jyafool.easylink.admin.dao.mapper")
public class EasyLinkAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyLinkAdminApplication.class, args);
    }
}

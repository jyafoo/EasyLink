package org.jyafool.easylink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 短链接中台服务启动类
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/9
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("org.jyafool.easylink.project.dao.mapper")
public class EasyLinkProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyLinkProjectApplication.class,args);
    }
}

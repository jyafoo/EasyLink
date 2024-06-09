package org.jyafool.easylink.aggregation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 聚合服务启动类
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/9
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "org.jyafool.easylink.admin",
        "org.jyafool.easylink.project"
})
@MapperScan(value = {
        "org.jyafool.easylink.project.dao.mapper",
        "org.jyafool.easylink.admin.dao.mapper"
})
public class AggregationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregationServiceApplication.class, args);
    }
}

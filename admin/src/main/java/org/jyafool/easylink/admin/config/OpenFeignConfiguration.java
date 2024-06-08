package org.jyafool.easylink.admin.config;

import feign.RequestInterceptor;
import org.jyafool.easylink.admin.common.biz.user.UserContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * openFeign 微服务调用传递用户信息配置
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@Configuration
public class OpenFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("username", UserContext.getUsername());
            template.header("userId", UserContext.getUserId());
            template.header("realName", UserContext.getRealName());
        };
    }
}

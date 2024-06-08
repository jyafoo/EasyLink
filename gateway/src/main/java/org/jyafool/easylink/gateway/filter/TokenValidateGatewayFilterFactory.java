/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jyafool.easylink.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import org.jyafool.easylink.gateway.config.Config;
import org.jyafool.easylink.gateway.dto.GatewayErrorResult;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * SpringCloud Gateway Token 拦截器
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/8
 */
@Component
public class TokenValidateGatewayFilterFactory extends AbstractGatewayFilterFactory<Config> {

    private final StringRedisTemplate stringRedisTemplate;

    public TokenValidateGatewayFilterFactory(StringRedisTemplate stringRedisTemplate) {
        super(Config.class);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 创建一个网关过滤器，用于验证请求是否包含有效的登录令牌。
     * 如果请求路径在白名单中，且请求头包含有效的用户名和令牌，则在请求头中添加用户ID和真实姓名。
     * 如果验证失败，返回未授权的响应。
     *
     * @param config 请求白名单配置，包含需要忽略验证的路径列表。
     * @return GatewayFilter 实例，用于处理请求验证。
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 获取当前请求信息
            ServerHttpRequest request = exchange.getRequest();
            String requestPath = request.getPath().toString();
            String requestMethod = request.getMethod().name();

            // 检查请求路径是否在白名单中
            if (!isPathInWhiteList(requestPath, requestMethod, config.getWhitePathList())) {
                // 从请求头中尝试获取用户名和令牌
                String username = request.getHeaders().getFirst("username");
                String token = request.getHeaders().getFirst("token");

                // 验证用户名和令牌是否存在并有效
                Object userInfo;
                if (StringUtils.hasText(username) && StringUtils.hasText(token) && (userInfo = stringRedisTemplate.opsForHash().get("short-link:login:" + username, token)) != null) {
                    // 解析用户信息，并在请求头中添加用户ID和真实姓名
                    JSONObject userInfoJsonObject = JSON.parseObject(userInfo.toString());
                    ServerHttpRequest.Builder builder = exchange.getRequest().mutate().headers(httpHeaders -> {
                        httpHeaders.set("userId", userInfoJsonObject.getString("id"));
                        httpHeaders.set("realName", URLEncoder.encode(userInfoJsonObject.getString("realName"), StandardCharsets.UTF_8));
                    });
                    // 继续处理请求
                    return chain.filter(exchange.mutate().request(builder.build()).build());
                }

                // 如果验证失败，返回未授权响应
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                    GatewayErrorResult resultMessage = GatewayErrorResult.builder()
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .message("Token validation error")
                            .build();
                    return bufferFactory.wrap(JSON.toJSONString(resultMessage).getBytes());
                }));
            }

            // 如果请求路径在白名单中，直接继续处理请求
            return chain.filter(exchange);
        };
    }


    /**
     * 判断请求路径是否在白名单中。
     *
     * 此方法用于检查给定的请求路径和方法是否符合预定义的白名单规则。
     * 白名单中的路径允许特定的请求方法通过，增强了系统的安全性。
     *
     * @param requestPath 请求的路径，用于匹配白名单中的路径前缀。
     * @param requestMethod 请求的方法，用于特定路径的请求方法检查。
     * @param whitePathList 白名单路径列表，包含所有允许的路径前缀。
     * @return 如果请求路径在白名单中或特定路径与请求方法匹配，则返回true；否则返回false。
     */
    private boolean isPathInWhiteList(String requestPath, String requestMethod, List<String> whitePathList) {
        // 检查白名单路径列表是否为空，以及是否存在匹配的路径前缀。
        // 同时检查特定的路径和请求方法是否匹配，该特定路径和方法组合被单独处理，可能出于特定的业务逻辑考虑。
        return (!CollectionUtils.isEmpty(whitePathList) && whitePathList.stream().anyMatch(requestPath::startsWith)) || (Objects.equals(requestPath, "/api/short-link/admin/v1/user") && Objects.equals(requestMethod, "POST"));
    }

}

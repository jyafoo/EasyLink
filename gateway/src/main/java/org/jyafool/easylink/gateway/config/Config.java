package org.jyafool.easylink.gateway.config;

import lombok.Data;

import java.util.List;

/**
 * 过滤器配置
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/8
 */
@Data
public class Config {

    /**
     * 白名单前置路径
     */
    private List<String> whitePathList;
}


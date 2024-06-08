package org.jyafool.easylink.gateway.dto;

/**
 * 网关错误返回信息
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/8
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网关错误返回信息
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayErrorResult {

    /**
     * HTTP 状态码
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String message;
}

package org.jyafool.easylink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jyafool.easylink.admin.common.serialize.PhoneDesensitizationSerializer;

/**
 * 用户返回参数响应
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
public class UserRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}

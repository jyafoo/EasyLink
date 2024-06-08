package org.jyafool.easylink.admin.dto.resp;

import lombok.Data;

/**
 * 用户返回参数响应
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@Data
public class UserActualRespDTO {

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
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}


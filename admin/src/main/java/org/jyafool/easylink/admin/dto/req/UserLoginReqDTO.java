package org.jyafool.easylink.admin.dto.req;

import lombok.Data;

/**
 * 用户登录请求参数
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@Data
public class UserLoginReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}

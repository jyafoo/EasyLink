package org.jyafool.easylink.admin.dto.req;

import lombok.Data;

/**
 * 回收站保存请求参数
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
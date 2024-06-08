package org.jyafool.easylink.admin.remote.dto.resp;

import lombok.Data;

/**
 * 短链接分组查询返回参数
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/8
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数量
     */
    private Integer shortLinkCount;
}

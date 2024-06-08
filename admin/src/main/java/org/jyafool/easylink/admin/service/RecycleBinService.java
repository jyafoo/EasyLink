package org.jyafool.easylink.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jyafool.easylink.admin.common.convention.result.Result;
import org.jyafool.easylink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.jyafool.easylink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * URL 回收站接口层
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
package org.jyafool.easylink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.jyafool.easylink.project.dao.entity.ShortLinkDO;
import org.jyafool.easylink.project.dto.req.ShortLinkPageReqDTO;
import org.jyafool.easylink.project.dto.req.ShortLinkRecycleBinPageReqDTO;

/**
 * 短链接持久层
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/9
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    /**
     * 短链接访问统计自增
     */
    void incrementStats(@Param("gid") String gid,
                        @Param("fullShortUrl") String fullShortUrl,
                        @Param("totalPv") Integer totalPv,
                        @Param("totalUv") Integer totalUv,
                        @Param("totalUip") Integer totalUip);

    /**
     * 分页统计短链接
     */
    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);

    /**
     * 分页统计回收站短链接
     */
    IPage<ShortLinkDO> pageRecycleBinLink(ShortLinkRecycleBinPageReqDTO requestParam);
}

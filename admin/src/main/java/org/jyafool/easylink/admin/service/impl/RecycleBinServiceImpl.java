package org.jyafool.easylink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.jyafool.easylink.admin.common.biz.user.UserContext;
import org.jyafool.easylink.admin.common.convention.exception.ServiceException;
import org.jyafool.easylink.admin.common.convention.result.Result;
import org.jyafool.easylink.admin.dao.entity.GroupDO;
import org.jyafool.easylink.admin.dao.mapper.GroupMapper;
import org.jyafool.easylink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.jyafool.easylink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.jyafool.easylink.admin.service.RecycleBinService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * URL 回收站接口实现层
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@Service(value = "recycleBinServiceImplByAdmin")
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;
    private final GroupMapper groupMapper;

    @Override
    public Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("用户无分组信息");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkActualRemoteService.pageRecycleBinShortLink(requestParam.getGidList(), requestParam.getCurrent(), requestParam.getSize());
    }
}

package org.jyafool.easylink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.jyafool.easylink.admin.common.convention.result.Result;
import org.jyafool.easylink.admin.common.convention.result.Results;
import org.jyafool.easylink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.jyafool.easylink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.jyafool.easylink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.jyafool.easylink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.jyafool.easylink.admin.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组控制层
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询短链接分组集合
     */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组名称
     */
    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * 删除短链接分组
     */
    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 排序短链接分组
     */
    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}

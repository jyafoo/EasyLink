package org.jyafool.easylink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 短链接分组唯一路由实体
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
@Data
@TableName("t_group_unique")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupUniqueDO {

    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;
}
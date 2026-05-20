package com.bilibili.myblogbackend.dto.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 图片管理表实体 Record
 */
@TableName("manage")
public record Manage(
        @TableId
        Long id,

        @TableField("typeId")
        Integer typeId,

        @TableField("image_name")
        String imageName,

        @TableField("self_introduce")
        String selfIntroduce
) {
    // 无参构造，兼容MP反射实例化
    public Manage() {
        this(null, null, null, null);
    }
}
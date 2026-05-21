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

        @TableField("type_id")
        Integer typeId,

        @TableField("image_name")
        String imageName,

        @TableField("self_introduce")
        String selfIntroduce
) {

}
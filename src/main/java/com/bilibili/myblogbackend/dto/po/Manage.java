package com.bilibili.myblogbackend.dto.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 图片管理表实体
 */
@Data
@TableName("manage")
public class Manage {

    @TableId
    private Long id;

    @TableField("type_id")
    private Integer typeId;

    @TableField("image_name")
    private String imageName;

    @TableField("self_introduce")
    private String selfIntroduce;

}

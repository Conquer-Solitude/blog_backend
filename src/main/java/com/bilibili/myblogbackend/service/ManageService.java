package com.bilibili.myblogbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.dto.po.Article;
import com.bilibili.myblogbackend.dto.po.Manage;
import com.bilibili.myblogbackend.mapper.ArticleMapper;
import com.bilibili.myblogbackend.mapper.ManageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManageService extends ServiceImpl<ManageMapper, Manage> {

    public List<Manage> getAllBackground() {
        return this.list(new QueryWrapper<Manage>().eq("type_id", 1));
    }
}

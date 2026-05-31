package com.bilibili.myblogbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bilibili.myblogbackend.dto.po.Manage;
import com.bilibili.myblogbackend.mapper.ManageMapper;
import com.bilibili.myblogbackend.util.oss.MinioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageService extends ServiceImpl<ManageMapper, Manage> {

    private final MinioUtils minioUtils;

    public List<Manage> getAllBackground() {
        return this.list(new QueryWrapper<Manage>().eq("type_id", 1));
    }

    public String uploadBackground(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = LocalDateTime.now().toString().replace(":", "-") + suffix;
        minioUtils.uploadImage(fileName, file);

        Manage manage = new Manage();
        manage.setTypeId(1);
        manage.setImageName(fileName);
        this.save(manage);

        return fileName;
    }
}

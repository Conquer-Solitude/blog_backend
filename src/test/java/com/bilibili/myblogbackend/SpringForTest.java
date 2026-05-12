package com.bilibili.myblogbackend;

import com.bilibili.myblogbackend.util.EmailUtils;
import com.bilibili.myblogbackend.util.oss.MinioUtils;
import io.minio.errors.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class SpringForTest {


    /**
     * MinIO/R2 上传功能测试类
     * 用于测试图片上传到 Cloudflare R2
     */


        @Autowired
        private MinioUtils minioUtils;
        @Autowired
        private EmailUtils emailUtils;

        /**
         * 测试上传图片到 R2
         * 使用前请确保：
         * 1. application.yml 中配置了正确的 R2 endpoint 和密钥
         * 2. 图片路径正确
         * 3. R2 Bucket 已创建且有写入权限
         */
        @Test
        public void testUploadImage() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InvalidResponseException, InterruptedException, InternalException {
            // ============ 配置区域 ============
            // 本地图片路径（请修改为你实际的图片路径）
        String localImagePath = "D:\\编程\\photograph\\love2.jpeg";
//            String localImagePath = "D:\\编程\\photograph\\Login.jpg";
            String localVideoPath = "D:\\temp\\Media\\97秒看香港回归25年变化_哔哩哔哩_bilibili (1).mp4";

            // 上传到 R2 后的文件名
            String uploadFileName = "test-image-" + System.currentTimeMillis() + ".jpeg";
            String uploadFileName2 = "test-image-" + System.currentTimeMillis() + ".mp4";
            // =================================

            File imageFile = new File(localImagePath);
            File videoFile = new File(localVideoPath);
            FileInputStream videoInputStream = new FileInputStream(videoFile);
            Path path = Paths.get(localVideoPath);
            String contentType = Files.probeContentType(path);
            System.out.println(contentType);
            MockMultipartFile file = new MockMultipartFile("file", videoFile.getName(),contentType ,videoInputStream );

            minioUtils.uploadPartFile(uploadFileName2, file);


       /* try (FileInputStream fis = new FileInputStream(imageFile)) {
            // 创建 MockMultipartFile 模拟上传文件
            MultipartFile multipartFile = new MockMultipartFile(
                    "file",                          // 表单字段名
                    imageFile.getName(),             // 原始文件名
                    "image/jpeg",                    // 内容类型
                    fis                              // 文件输入流
            );

            // 执行上传
            System.out.println("🚀 开始上传...");
            minioUtils.uploadImage(uploadFileName, multipartFile);


            System.out.println("📎 文件已保存为: " + uploadFileName);

        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }*/
}


        @SneakyThrows
        @Test
        public void testEmail(){
            emailUtils.sendMessage("459038176@qq.com");
        }


}

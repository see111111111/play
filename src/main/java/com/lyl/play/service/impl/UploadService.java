package com.lyl.play.service.impl;

import com.lyl.play.common.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {

    @Value("${file.upload.path}")
    private File uploadPath;

    @Value("${file.upload.url-path}")
    private String urlPath;

    public List<String> upload(@ApiIgnore MultipartHttpServletRequest request, String innerPathName) {
        innerPathName = innerPathName == null ? "common" : innerPathName;
        innerPathName = innerPathName.replace("/", "");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        innerPathName = innerPathName + "/" + date;

        List<String> ret = new ArrayList<>();
        for (MultipartFile mfile : request.getFileMap().values()) {
            String fileName = mfile.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(new File(uploadPath, innerPathName), fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                mfile.transferTo(dest);
            } catch (IOException e) {
                log.error("文件上传异常：{}", e.getMessage());
                throw new BusinessException("文件上传异常：" + e.getMessage());
            }
            String filename = urlPath + innerPathName + "/" + fileName;
            ret.add(filename);
        }

        return ret;
    }

}

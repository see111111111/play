package com.lyl.play.controller;

import com.lyl.play.mapper.TwitterNftMapper;
import com.lyl.play.service.TwitterNftService;
import com.lyl.play.service.impl.IpfsUploadService;
import com.lyl.play.service.impl.UploadService;
import com.lyl.play.vo.PageResult;
import com.lyl.play.vo.ResponseData;
import com.lyl.play.vo.req.TwitterNftReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cyc")
public class CycController {

    @Autowired
    private TwitterNftService twitterNftService;

    @Autowired
    private TwitterNftMapper twitterNftMapper;

    @Autowired
    private IpfsUploadService ipfsUploadService;

    @Autowired
    private UploadService uploadService;

    @PostMapping({"list"})
    public ResponseData<PageResult> listData(TwitterNftReq req){
        if (StringUtils.isBlank(req.getMintUser()) || StringUtils.isBlank(req.getReceiveUser()))
            return ResponseData.error("缺失参数");
        return twitterNftService.attaList(req);
    }

}

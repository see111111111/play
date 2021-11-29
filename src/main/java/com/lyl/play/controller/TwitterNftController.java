package com.lyl.play.controller;

import com.lyl.play.service.TwitterNftService;
import com.lyl.play.vo.PageResult;
import com.lyl.play.vo.ResponseData;
import com.lyl.play.vo.req.AddressReq;
import com.lyl.play.vo.req.TwitterNftReq;
import com.lyl.play.vo.req.UpReq;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("twitter")
public class TwitterNftController {

    @Autowired
    private TwitterNftService twitterNftService;

    @ApiOperation("产品列表")
    @GetMapping("list")
    @ResponseBody
    public ResponseData<PageResult> list(TwitterNftReq req){
        if (StringUtils.isBlank(req.getMintUser()) || StringUtils.isBlank(req.getOrderNo()))
        return ResponseData.error("参数缺失");
        return ResponseData.success(twitterNftService.list(req));
    }

    @ApiOperation("账号绑定钱包")
    @PostMapping("bind")
    @ResponseBody
    public ResponseData bind(AddressReq req){
        if (StringUtils.isBlank(req.getAddress()) || StringUtils.isBlank(req.getMintUser()))
        return ResponseData.error("参数缺失");
        twitterNftService.bind(req);
        return ResponseData.success();
    }

    @ApiOperation("更新nft状态")
    @PostMapping("upstatus")
    @ResponseBody
    public ResponseData upStatus(UpReq req){
        if (req.getTokenId()==null)return ResponseData.error("缺失参数");
        int a=twitterNftService.upStatus(req);
        if (a<=0)return ResponseData.error("修改失败");
        return ResponseData.success();
    }

}

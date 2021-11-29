package com.lyl.play.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyl.play.entity.TwitterNftRecord;
import com.lyl.play.vo.PageResult;
import com.lyl.play.vo.req.AddressReq;
import com.lyl.play.vo.req.TwitterNftReq;
import com.lyl.play.vo.req.UpReq;

public interface TwitterNftService  {
    PageResult list(TwitterNftReq req);

    void bind(AddressReq req);

    int upStatus(UpReq req);
}

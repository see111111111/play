package com.lyl.play.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyl.play.entity.TwitterNftRecord;
import com.lyl.play.vo.PageResult;
import com.lyl.play.vo.ResponseData;
import com.lyl.play.vo.req.AddressReq;
import com.lyl.play.vo.req.NewNftReq;
import com.lyl.play.vo.req.TwitterNftReq;
import com.lyl.play.vo.req.UpReq;
import com.lyl.play.vo.res.RecordResMes;

import java.util.List;

public interface TwitterNftService  {
    PageResult list(TwitterNftReq req);

    void bind(AddressReq req);

    int upStatus(UpReq req);


    int accept(UpReq req);

    List<RecordResMes> mint(Long tokenId, int i);

    void createNft(NewNftReq req);


    ResponseData<PageResult> attaList(TwitterNftReq req);
}

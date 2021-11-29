package com.lyl.play.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyl.play.entity.TwitterAccount;
import com.lyl.play.entity.TwitterNftRecord;
import com.lyl.play.mapper.TwitterAccountMapper;
import com.lyl.play.mapper.TwitterNftMapper;
import com.lyl.play.service.TwitterNftService;
import com.lyl.play.utils.PageUtils;
import com.lyl.play.vo.PageResult;
import com.lyl.play.vo.req.AddressReq;
import com.lyl.play.vo.req.TwitterNftReq;
import com.lyl.play.vo.req.UpReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TwitterNftServiceImpl extends ServiceImpl<TwitterNftMapper, TwitterNftRecord> implements TwitterNftService {

    @Autowired
    private TwitterNftMapper twitterNftMapper;

    @Autowired
    private TwitterAccountMapper twitterAccountMapper;

    @Override
    public PageResult list(TwitterNftReq req) {

        QueryWrapper<TwitterNftRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mint_user", req.getMintUser());
        queryWrapper.eq("type", req.getType());
        Page<TwitterNftRecord> userPage = new Page(req.getCurrent(), req.getPageSize());
        userPage = baseMapper.selectPage(userPage, queryWrapper);
        PageResult pageResult = PageUtils.convertToResult(userPage);

        List list=new ArrayList();
        for (int i = 0; i <pageResult.getRecords().size() ; i++) {
            TwitterNftRecord twitterNftRecord = (TwitterNftRecord) pageResult.getRecords().get(i);
            if (twitterNftRecord.getCreatorTag()==1){
                TwitterNftRecord t = baseMapper.selectByOrderNo(twitterNftRecord.getOrderNo());
                twitterNftRecord.setOhterAddress(t.getAddress());
                twitterNftRecord.setOhterUser(t.getMintUser());
            }
            list.add(twitterNftRecord);
        }
        pageResult.setRecords(list);
        return pageResult;
    }

    @Override
    public void bind(AddressReq req) {
        TwitterAccount twitterAccount=twitterAccountMapper.bind(req.getAddress(),req.getMintUser());
        if (twitterAccount==null){
            TwitterAccount twitterAccount1=new TwitterAccount();
            twitterAccount1.setAddress(req.getAddress());
            twitterAccount1.setTwitterNo(req.getMintUser());
            twitterAccount1.setCreateDate(new Date());
            twitterAccountMapper.insert(twitterAccount1);
        }
    }

    @Override
    public int upStatus(UpReq req) {

        return twitterNftMapper.upStatus(req.getTokenId(),req.getStatus());
    }
}

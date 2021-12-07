package com.lyl.play.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
import com.lyl.play.vo.req.NewNftReq;
import com.lyl.play.vo.req.TwitterNftReq;
import com.lyl.play.vo.req.UpReq;
import com.lyl.play.vo.res.RecordResMes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TwitterNftServiceImpl extends ServiceImpl<TwitterNftMapper, TwitterNftRecord> implements TwitterNftService {

    @Autowired
    private TwitterNftMapper twitterNftMapper;

    @Autowired
    private TwitterAccountMapper twitterAccountMapper;

    @Autowired
    private IpfsUploadService ipfsUploadService;

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

    @Override
    public int accept(UpReq req) {
        if (baseMapper.updateRecordById(req.getTokenId(),req.getAddress())>0){
            List<RecordResMes> recordResMes=baseMapper.selectRecordById(req.getTokenId());
            if (recordResMes!=null){
                return baseMapper.updateByOrderNo(recordResMes.get(0).getOrderNo(),1);
            }
        }
        return 0;
    }

    @Override
    public List<RecordResMes> mint(Long tokenId, int i) {
        List<RecordResMes> recordResMes=baseMapper.selectRecordById(tokenId);
        if (recordResMes.isEmpty()){
            return recordResMes;
        }
        baseMapper.updateByOrderNo(recordResMes.get(0).getOrderNo(),i);
        return recordResMes;
    }

    @Override
    @Transactional
    public void createNft(NewNftReq req) {
        String orderNo = UUID.randomUUID().toString().toUpperCase();
        TwitterNftRecord t = new TwitterNftRecord();
        t.setOrderNo(orderNo);
        JSONObject nftContent = new JSONObject();
        nftContent.put("name", req.getName());
        nftContent.put("description", req.getDescription());
        nftContent.put("picturePath", req.getPicturePath());
        t.setNftContent(nftContent.toString());
        t.setType(req.getType());
        t.setSourceFileIpfs(req.getSourceFileIpfs());

        JSONObject obj = JSONUtil.createObj();
        obj.put("fileName", req.getSourceFileIpfs());
        obj.put("tokenId", req.getSourceFileIpfs());
        obj.put("image", req.getSourceFileIpfs());
        obj.put("name", req.getName());
        obj.put("description", req.getDescription());
        String metaDataIpfs = ipfsUploadService.uploadIpfsForJson(obj);
        t.setMetadataIpfs(metaDataIpfs);
        t.setCreateTime(new Date());
        t.setUpdateTime(new Date());

        if (req.getType() == 0 || req.getType() == 1) {
            t.setMintUser(req.getMintUser());
            t.setAddress(req.getAddress());
            t.setCreatorTag(0);
            this.baseMapper.insert(t);
        } else {
            //给自己
            t.setMintUser(req.getMintUser());
            t.setAddress(req.getAddress());
            t.setCreatorTag(0);
            this.baseMapper.insert(t);
            //给别人
            TwitterNftRecord twitterNftRecord = new TwitterNftRecord();
            try {
                BeanUtils.copyProperties(twitterNftRecord, t);
            } catch (Exception e) {
                e.getMessage();
            }
            twitterNftRecord.setAddress(null);
            twitterNftRecord.setMintUser(req.getReceiveUser());
            twitterNftRecord.setCreatorTag(1);
            this.baseMapper.insert(twitterNftRecord);
        }


    }



}

package com.lyl.play.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyl.play.entity.TwitterNftRecord;
import com.lyl.play.vo.req.UpReq;
import com.lyl.play.vo.res.RecordResMes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TwitterNftMapper extends BaseMapper<TwitterNftRecord> {

    TwitterNftRecord selectByOrderNo(String orderNo);

    int upStatus(@Param("tokenId") Long tokenId,@Param("status") int status);


    int updateRecordById(@Param("tokenId") Long tokenId,@Param("address") String address);

    List<RecordResMes> selectRecordById(Long tokenId);

    int updateByOrderNo(@Param("orderNo") String orderNo,@Param("status") int i);

    List<TwitterNftRecord> selectNoPage(TwitterNftRecord t);
}

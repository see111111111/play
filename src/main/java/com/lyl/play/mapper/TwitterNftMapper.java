package com.lyl.play.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyl.play.entity.TwitterNftRecord;
import org.apache.ibatis.annotations.Param;

public interface TwitterNftMapper extends BaseMapper<TwitterNftRecord> {

    TwitterNftRecord selectByOrderNo(String orderNo);

    int upStatus(@Param("tokenId") Long tokenId,@Param("status") int status);
}

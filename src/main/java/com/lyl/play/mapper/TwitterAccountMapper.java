package com.lyl.play.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyl.play.entity.TwitterAccount;
import org.apache.ibatis.annotations.Param;

public interface TwitterAccountMapper extends BaseMapper<TwitterAccount> {
    TwitterAccount bind(@Param("address") String address,@Param("mintUser") String mintUser);
}

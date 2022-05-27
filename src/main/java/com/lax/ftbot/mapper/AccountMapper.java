package com.lax.ftbot.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lax.ftbot.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 查询xdd数据库中所有的QQ
     * @return
     */
    List<String> selectQQGroup();
}

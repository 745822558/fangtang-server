package com.lax.ftbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lax.ftbot.pojo.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    /**
     * 查询当前QQ是否存在
     * @param qq
     * @return
     */
    Integer selectqqIsExist(String qq);

    /**
     * 根据QQ查询用户信息
     */
    UserAccount selectUserAccountByQQ(String qq);
}

package com.lax.ftbot.service;

import com.lax.ftbot.pojo.UserAccount;
import net.lz1998.pbbot.bot.Bot;

/**
 * @ClassName UserAccountService
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 14:22
 **/
public interface UserAccountService {

    /**
     * 同步xdd数据库
     */
    void syncXddToFtDateBase(Bot bot, long userId);

    /**
     * 查询当前QQ是否存在
     */
    boolean qqIsExist(String qq);

    /**
     * 插入用户信息
     */
    void insertUserAccount(UserAccount userAccount);

    /**
     * 根据QQ查询用户信息
     */
    UserAccount selectUserAccountByQQ(String qq, String wx);

    /**
     * 检测账号
     */
    void checkAccount(Bot bot, long userId);
}

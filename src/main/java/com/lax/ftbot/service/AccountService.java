package com.lax.ftbot.service;

import com.lax.ftbot.pojo.Account;

import java.util.List;

public interface AccountService {
    /**
     * 获取全部有效用户
     *
     * @return
     */
    List<Account> getAllAccount();

    /**
     * 查询xdd数据库中全部QQ
     *
     * @return
     */
    List<String> getQQByGroup();

    /**
     * 更新用户
     */
    void updatePtKey(Account account);
}

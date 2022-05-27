package com.lax.ftbot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lax.ftbot.mapper.AccountMapper;
import com.lax.ftbot.pojo.Account;
import com.lax.ftbot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/4/27 14:17
 **/
@Service
@DS("xdd")

public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> getAllAccount() {
        LambdaQueryWrapper<Account> wrapper = Wrappers.lambdaQuery();
        return accountMapper.selectList(wrapper);
    }

    @Override
    public List<String> getQQByGroup() {
        return accountMapper.selectQQGroup();
    }

    @Override
    public void updatePtKey(Account account) {
        accountMapper.updateById(account);
    }
}

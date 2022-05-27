package com.lax.ftbot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lax.ftbot.mapper.UserAccountMapper;
import com.lax.ftbot.pojo.Account;
import com.lax.ftbot.pojo.JDAccount;
import com.lax.ftbot.pojo.UserAccount;
import com.lax.ftbot.qbot.spend.SpendMessage;
import com.lax.ftbot.service.AccountService;
import com.lax.ftbot.service.JDAccountService;
import com.lax.ftbot.service.UserAccountService;
import com.lax.ftbot.utils.IdGenerator.IdGenerator;
import net.lz1998.pbbot.bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserAccountServiceImpl
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 14:22
 **/
@Service
@DS("ft")

public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private SpendMessage spendMessage;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JDAccountService jdAccountService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private IdGenerator idGenerator;

    /**
     * 同步xdd数据库
     */
    @Override
    public void syncXddToFtDateBase(Bot bot, long userId) {
        //查询
        List<String> qqList = accountService.getQQByGroup();
        //查询全部有效账号
        List<Account> accountList = accountService.getAllAccount();

        spendMessage.SpendTextMessage(bot, userId, "当前获取到xdd数据库【" + accountList.size() + "】个账号，正在同步数据中...");

        //插入QQ到新库
        for (String qq : qqList) {
            if (!qqIsExist(qq)) {
                UserAccount account = new UserAccount();
                account.setId(idGenerator.genId());
                account.setQq(qq);
                account.setWx(null);
                insertUserAccount(account);
            }
        }

        //插入账号到新库
        for (Account account : accountList) {
            if (!jdAccountService.ptpinIsExist(account.getPtPin())) {
                UserAccount userAccount = selectUserAccountByQQ(account.getQq(), null);
                JDAccount jdAccount = new JDAccount();
                jdAccount.setId(idGenerator.genId());
                jdAccount.setUserId(userAccount != null ? userAccount.getId() : null);
                jdAccount.setPriority(account.getPriority());
                jdAccount.setPtPin(account.getPtPin());
                jdAccount.setPtKey(account.getPtKey());
                jdAccount.setWskey(account.getWsKey());
                jdAccount.setRemark(account.getRemark());
                jdAccount.setIsValid("0".equals(account.getAvailable()) ? true : false);
                jdAccount.setIsMain(null);
                jdAccount.setNickname(account.getNickName());
                jdAccount.setPushPlus(account.getPushPlus());
                jdAccountService.insertJdAccount(jdAccount);
            }
        }
        spendMessage.SpendTextMessage(bot, userId, "正在进行账号检测，请稍后...");
        spendMessage.SpendTextMessage(bot, userId, "xdd数据库同步完成！");
    }

    /**
     * 查询当前QQ是否存在
     *
     * @param qq
     * @return
     */
    @Override
    public boolean qqIsExist(String qq) {
        return userAccountMapper.selectqqIsExist(qq) > 0 ? true : false;
    }

    /**
     * 插入用户信息
     *
     * @param userAccount
     */
    @Override
    public void insertUserAccount(UserAccount userAccount) {
        userAccountMapper.insert(userAccount);
    }


    /**
     * 通过QQ查询用户信息
     *
     * @param qq
     * @return
     */
    @Override
    public UserAccount selectUserAccountByQQ(String qq, String wx) {
        LambdaQueryWrapper<UserAccount> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotBlank(qq), UserAccount::getQq, qq);
        queryWrapper.eq(StringUtils.isNotBlank(wx), UserAccount::getWx, wx);
        queryWrapper.last("limit 1");
        return userAccountMapper.selectOne(queryWrapper);
    }

    /**
     * 检测账号
     * @param bot
     * @param userId
     */
    @Override
    public void checkAccount(Bot bot, long userId) {

    }
}

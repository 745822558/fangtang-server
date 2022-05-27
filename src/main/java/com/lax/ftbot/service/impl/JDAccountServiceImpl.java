package com.lax.ftbot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lax.ftbot.mapper.JDAccountMapper;
import com.lax.ftbot.pojo.JDAccount;
import com.lax.ftbot.service.JDAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName JDAccountServiceImpl
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 15:32
 **/
@Service
@DS("ft")
@Transactional(rollbackFor = Exception.class)
public class JDAccountServiceImpl implements JDAccountService {

    @Autowired
    private JDAccountMapper jdAccountMapper;

    @Override
    public boolean ptpinIsExist(String ptpin) {
        return jdAccountMapper.ptpinIsExist(ptpin) > 0 ? true : false;
    }

    /**
     * 插入JD信息
     *
     * @param jdAccount
     */
    @Override
    public void insertJdAccount(JDAccount jdAccount) {
        jdAccountMapper.insert(jdAccount);
    }
}

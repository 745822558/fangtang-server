package com.lax.ftbot.service;

import com.lax.ftbot.pojo.JDAccount;

/**
 * @ClassName JDAccountService
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/20 15:31
 **/
public interface JDAccountService {
    /**
     * ptpin是否存在
     */
    boolean ptpinIsExist(String ptpin);
    /**
     * 插入JD信息
     */
    void insertJdAccount(JDAccount jdAccount);
}

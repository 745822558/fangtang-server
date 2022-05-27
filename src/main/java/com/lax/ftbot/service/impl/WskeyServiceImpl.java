package com.lax.ftbot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lax.ftbot.pojo.Account;
import com.lax.ftbot.qbot.spend.SpendMessage;
import com.lax.ftbot.service.AccountService;
import com.lax.ftbot.service.WskeyService;
import com.lax.ftbot.utils.OkHttpUtils;
import net.lz1998.pbbot.bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

/**
 * @ClassName WskeyServiceImpl
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/18 10:12
 **/
@Service
public class WskeyServiceImpl implements WskeyService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private SpendMessage spendMessage;
    @Autowired
    private OkHttpUtils okHttpUtils;

    @Override
    public void updateAllPtKeyFromWskey(Bot bot, long userId) {
        List<Account> accountList = accountService.getAllAccount();
        spendMessage.SpendTextMessage(bot, userId, "当前获取到【" + accountList.size() + "】个有效账号，正在逐个更新ptkey...");
        for (Account account : accountList) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException("线程休眠异常，请联系作者并尝试重启程序！");
            }
            String status = updatePtKeyFromWskey(account);
            if (status.equals("success")) {
                spendMessage.SpendTextMessage(bot, userId, "更新账号" + account.getPtPin() + "的ptkey成功！");
            }
        }
        spendMessage.SpendTextMessage(bot, userId, "更新完毕！");
    }

    public String updatePtKeyFromWskey(Account account) {
        //云端地址检查
        String url = new String(Base64.getDecoder().decode("aHR0cDovLzQzLjEzNS45MC4yMy8="));
//        System.out.println(url);
        //获取ua
        JSONObject jsonObject1 = OkHttpUtils.builder().url(url + "check_api").
                addHeader("authorization", "Bearer Shizuku")
                .addHeader("Content-Type", "application/json")
                .get()
                .jsonSync();
//        System.out.println(jsonObject1);
        //获取参数
        JSONObject jsonObject2 = OkHttpUtils.builder().url(url + "genToken").
                addHeader("User-Agent", jsonObject1.getString("User-Agent"))
                .addHeader("Content-Type", "application/json")
                .get()
                .jsonSync();
//        System.out.println(jsonObject2);
        //拼接url
        url = "https://api.m.jd.com/client.action?";
        for (String key : jsonObject2.keySet()) {
            url += key + "=" + jsonObject2.getString(key) + "&";
        }
        url = url.substring(0, url.length() - 1);

        JSONObject jsonObject3 = OkHttpUtils.builder()
                .url(url)
                .addParam("body", "%7B%22to%22%3A%22https%253a%252f%252fplogin.m.jd.com%252fjd-mlogin%252fstatic%252fhtml%252fappjmp_blank.html%22%7D")
                .addHeader("Cookie", "pin=" + account.getPtPin() + ";wskey=" + account.getWsKey() + ";")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Charset", "UTF-8")
                .addHeader("User-Agent", jsonObject1.getString("User-Agent"))
                .post(null)
                .jsonSync();
        System.out.println(jsonObject3);


        List<String> cookieList = OkHttpUtils.builder()
                .url("https://un.m.jd.com/cgi-bin/app/appjmp")
                .addParam("tokenKey", jsonObject3.getString("tokenKey"))
                .addParam("to", "https://plogin.m.jd.com/jd-mlogin/static/html/appjmp_blank.html")
                .addHeader("User-Agent", jsonObject1.getString("User-Agent"))
                .addHeader("Accept", "accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("x-requested-with", "com.jingdong.app.mall")
                .get()
                .getCookie();
        System.out.println(cookieList);
        final String[] cookie = {""};
        cookieList.forEach(setCookie -> {
            int ptkeyIndex = setCookie.indexOf("pt_key=");
            if (ptkeyIndex != -1) {
                cookie[0] = setCookie.substring(ptkeyIndex + 7, setCookie.indexOf(";", ptkeyIndex));
            }
        });
        //pt_key=app_openAAJihLLsADB-bt_2O-MeERYL-9XQF19SoQDABH_pED6EQG94_x_u9c4tqj2uLVcNn2OmZdRGNFA;
        // return app_openAAJihLLsADB-bt_2O-MeERYL-9XQF19SoQDABH_pED6EQG94_x_u9c4tqj2uLVcNn2OmZdRGNFA
        account.setPtKey(cookie[0]);
        accountService.updatePtKey(account);
        return "success";
    }
}

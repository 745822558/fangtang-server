package com.lax.ftbot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.lax.ftbot.service.ActiviteService;
import com.lax.ftbot.utils.OkHttpUtils;
import net.lz1998.pbbot.bot.Bot;
import org.springframework.stereotype.Service;

/**
 * @ClassName ActiviteServiceImpl
 * @Description TODO
 * @Author liAnXin
 * @Date 2022/5/24 9:17
 **/
@Service
@DS("ft")
public class ActiviteServiceImpl implements ActiviteService {

    /**
     * 618金币助力
     */
    @Override
    public void gold618(Bot bot, long userId, int sum, String body) {
        JSONObject jsonObject = OkHttpUtils.builder().url("https://api.m.jd.com/client.action?functionId=promote_getHomeData")
                .addParam("functionId", "promote_getHomeData&client=m&clientVersion=-1&appid=signed_wh5&body={}")
                .addHeader("Host", "api.m.jd.com")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "81")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Origin", "https://m.jd.com")
                .addHeader("X-Requested-With", "com.jingdong.app.mall")
                .addHeader("Sec-Fetch-Site", "same-site")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Referer", "https://wbbny.m.jd.com/")
                .addHeader("Accept-Encoding", "deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .addHeader("User-Agent", "jdapp;android;11.0.2;;;appBuild/97565;ef/1;ep/%7B%22hdid%22%3A%22JM9F1ywUPwflvMIpYPok0tt5k9kW4ArJEU3lfLhxBqw%3D%22%2C%22ts%22%3A1653356318043%2C%22ridx%22%3A-1%2C%22cipher%22%3A%7B%22sv%22%3A%22CJS%3D%22%2C%22ad%22%3A%22CJHvEWDtCtHsYJUnZtS0CK%3D%3D%22%2C%22od%22%3A%22DQG4CzHvDNY3YtG3ZWVvEG%3D%3D%22%2C%22ov%22%3A%22CzO%3D%22%2C%22ud%22%3A%22CJHvEWDtCtHsYJUnZtS0CK%3D%3D%22%7D%2C%22ciphertype%22%3A5%2C%22version%22%3A%221.2.0%22%2C%22appname%22%3A%22com.jingdong.app.mall%22%7D;jdSupportDarkMode/0;Mozilla/5.0 (Linux; Android 12; 2201122C Build/SKQ1.211006.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/89.0.4389.72 MQQBrowser/6.2 TBS/046011 Mobile Safari/537.36").post(null).jsonSync();
        System.out.println(jsonObject);

    }
}



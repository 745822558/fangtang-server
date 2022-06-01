package com.lax.ftbot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lax.ftbot.service.SignService;
import com.lax.ftbot.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
@Slf4j
public class SignController {

    @Autowired
    SignService signService;


    @PostMapping
    public R getSign(String functionId, @RequestBody String body) {
        log.info("Content-Type: application/json");
        log.info("请求地址为：sign?functionId=getCcFeedInfo");
        log.info("body为json格式");
        log.info("functionId:{},body:{}", functionId, body);
        if (StrUtil.isEmpty(functionId) || StrUtil.isEmpty(body)) {
            return R.fail(-1, "参数异常！");
        }
        try {
            JSONObject jsonObject = JSONUtil.parseObj(body);
        } catch (Exception e) {
            System.out.println(e);
            return R.fail(-1, "body参数异常！");
        }
        String uuid = StrUtil.uuid();
        String sign = signService.getSign(functionId, uuid, body);
        String res = "&clientVersion=11.0.2&build=97565&client=android&partner=yyds3&sdkVersion=30&lang=zh_CN&harmonyOs=0&networkType=wifi&oaid=" + uuid + "&uuid=" + uuid + "&" + sign;
        return R.success("成功！", res);
    }

}

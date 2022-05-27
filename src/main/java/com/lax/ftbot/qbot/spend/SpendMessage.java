package com.lax.ftbot.qbot.spend;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.utils.Msg;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpendTextMessage
 * @Description 发送文本消息
 * @Author liAnXin
 * @Date 2022/4/27 13:09
 **/
@Component
public class SpendMessage {
    public void SpendTextMessage(Bot bot, long userId, String text) {
        Msg msg = Msg.builder()
                .text(text);
        bot.sendPrivateMsg(userId, msg, false);
    }
}

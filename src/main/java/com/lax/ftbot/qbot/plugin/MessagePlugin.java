package com.lax.ftbot.qbot.plugin;

import com.lax.ftbot.service.ActiviteService;
import com.lax.ftbot.service.UserAccountService;
import com.lax.ftbot.service.WskeyService;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePlugin extends BotPlugin {
    @Autowired
    private WskeyService wskeyService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ActiviteService activiteService;

    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        long userId = event.getUserId();
        String message = event.getRawMessage();
        if ("更新账号".equals(message)) {
            wskeyService.updateAllPtKeyFromWskey(bot, userId);
        } else if ("同步xdd数据库".equals(message)) {
            userAccountService.syncXddToFtDateBase(bot, userId);
        } else if ("检测账号".equals(message)) {
            userAccountService.checkAccount(bot, userId);
        } else if ("618金币助力".equals(message.substring(0, 7))) {
            int beginIndex = message.indexOf(" ");
            int endIndex = message.indexOf(" ", beginIndex + 1);
            int sum = Integer.parseInt(message.substring(beginIndex + 1, endIndex));
            String body = message.substring(endIndex + 1);
            activiteService.gold618(bot, userId, sum, body);
        }

        return MESSAGE_BLOCK;
    }
//    @Override
//    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
//        long userId = event.getUserId();
//        Msg msg = Msg.builder()
//                .face(1)
//                .text("hello1")
//                .text("hello2")
//                .image("https://www.baidu.com/img/flexible/logo/pc/result@2.png");
//        bot.sendPrivateMsg(userId, msg, false);
//        return MESSAGE_BLOCK;
//    }
}

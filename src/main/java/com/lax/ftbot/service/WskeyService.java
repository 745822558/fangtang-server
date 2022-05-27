package com.lax.ftbot.service;

import net.lz1998.pbbot.bot.Bot;

public interface WskeyService {
    /**
     * 通过wskey更新全部账号
     * @return
     */
    void updateAllPtKeyFromWskey(Bot bot, long userId);

}

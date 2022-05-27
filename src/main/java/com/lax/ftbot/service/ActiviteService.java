package com.lax.ftbot.service;

import net.lz1998.pbbot.bot.Bot;

public interface ActiviteService {
    /**
     * 618金币助力
     */
    void gold618(Bot bot, long userId, int sum, String body);
}

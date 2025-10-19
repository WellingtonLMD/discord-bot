package com.wlmd.discord_bot.service;

import org.springframework.stereotype.Service;

@Service
public class PingService {

    public String getPingResponse() {
        return "🏓 Pong!";
    }
}
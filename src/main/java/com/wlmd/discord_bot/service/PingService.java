package com.wlmd.discord_bot.service;

// PING command service, used for testing

import org.springframework.stereotype.Service;

@Service
public class PingService {

    public String getPingResponse() {
        return "🏓 Pong!";
    }
}
package com.wlmd.discord_bot.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;
import java.util.List;

@Configuration
public class JdaConfig {

    @Value("${discord.bot-token}")
    private String token;

    private final List<ListenerAdapter> listeners;

    public JdaConfig(List<ListenerAdapter> listeners) {
        this.listeners = listeners;
    }

    @Bean
    public JDA jda() throws Exception {
    	EnumSet<GatewayIntent> intents = EnumSet.allOf(GatewayIntent.class);


        JDABuilder builder = JDABuilder.create(token, intents)
                .setMemberCachePolicy(MemberCachePolicy.ALL);

        listeners.forEach(builder::addEventListeners);

        return builder.build().awaitReady();
    }
}

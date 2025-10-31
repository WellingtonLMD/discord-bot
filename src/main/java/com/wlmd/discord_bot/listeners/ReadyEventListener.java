package com.wlmd.discord_bot.listeners;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// Just log into the console to see that the bot is up and running correctly.
// TODO: Make the startup log more robust

@Component
public class ReadyEventListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Discord bot is ready! Logged in as:  " 
            + event.getJDA().getSelfUser().getName());
    }
}

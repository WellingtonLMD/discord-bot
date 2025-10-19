package com.wlmd.discord_bot.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;
import com.wlmd.discord_bot.service.PingService;
import com.wlmd.discord_bot.service.TestService;
import com.wlmd.discord_bot.service.LoadServerUsers;
import org.jetbrains.annotations.NotNull;

@Component
public class SlashCommandListener extends ListenerAdapter {

    private final PingService pingService;
    private final TestService testService;
    private final LoadServerUsers loadServerUsers;

    public SlashCommandListener(PingService pingService, TestService testService, LoadServerUsers loadServerUsers ) {
        this.pingService = pingService;
        this.testService = testService;
        this.loadServerUsers = loadServerUsers;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    	String name = event.getName();

        switch (name) {
        case "ping":
        	String pingResponse = pingService.getPingResponse();
        	event.reply(pingResponse).queue();
        	break;
        case "teste":
        	String testResponse = testService.getTestResponse();
        	loadServerUsers.loadUsers(event);
        	event.reply(testResponse).queue();
        	break;
        }
    }


}
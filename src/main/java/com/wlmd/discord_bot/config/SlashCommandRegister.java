package com.wlmd.discord_bot.config;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

@Component
public class SlashCommandRegister {

    private final JDA jda;

    public SlashCommandRegister(JDA jda) {
        this.jda = jda;
    }

    @PostConstruct
    public void registerCommands() {
        // Deletes all old commands and only records new ones
    	// Every slash command must be registered here.
    	// TODO: See if there's a better way to do it.
        jda.updateCommands().addCommands(
            Commands.slash("ping", "Respond with Pong!"),
            Commands.slash("teste", "Respond with Teste 123!")
        ).queue(success -> {
            System.out.println("Slash commands successfully updated!");
        }, error -> {
            System.err.println("Failed to update commands:  " + error.getMessage());
        });
    }
}

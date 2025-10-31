package com.wlmd.discord_bot.config;

import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

@Component
public class SlashCommandRegister extends ListenerAdapter {

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        var jda = event.getJDA(); // pega a instância aqui, sem injeção

        System.out.println("✅ JDA is ready! Registering slash commands...");

        jda.updateCommands().addCommands(
            Commands.slash("ping", "Respond with Pong!"),
            Commands.slash("load_deals", "Respond with Teste 123!"),
            Commands.slash("get_deals", "Respond with Teste 123!"),
            Commands.slash("test", "Used for tests")
        ).queue(
            success -> System.out.println("Slash commands successfully updated!"),
            error -> System.err.println("Failed to update commands: " + error.getMessage())
        );
    }
}

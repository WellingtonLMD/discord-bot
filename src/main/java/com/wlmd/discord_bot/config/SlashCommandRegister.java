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
        // Apaga todos os comandos antigos e registra apenas os novos
        jda.updateCommands().addCommands(
            Commands.slash("ping", "Responde com Pong!"),
            Commands.slash("teste", "Responde com Teste 123!")
        ).queue(success -> {
            System.out.println("✅ Comandos slash atualizados com sucesso!");
        }, error -> {
            System.err.println("❌ Falha ao atualizar comandos: " + error.getMessage());
        });
    }
}

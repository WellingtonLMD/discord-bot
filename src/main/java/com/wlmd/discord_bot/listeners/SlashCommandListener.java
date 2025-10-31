package com.wlmd.discord_bot.listeners;

// Listener for slash commands, where all slash commands are received and their services are called to execute the actions.
// TODO: See if this is the most efficient way to do this.
// TODO: Handle possible exceptions that may be generated 
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;
import com.wlmd.discord_bot.service.PingService;
import com.wlmd.discord_bot.service.TestService;
import com.wlmd.discord_bot.service.LoadGuildMembersService;
import com.wlmd.discord_bot.service.SteamDealsService;
import org.jetbrains.annotations.NotNull;
import com.wlmd.discord_bot.dto.SteamDealsDto;
import java.util.List;
import net.dv8tion.jda.api.entities.MessageEmbed;

@Component
public class SlashCommandListener extends ListenerAdapter {

    private final PingService pingService;
    private final TestService testService;
    private final LoadGuildMembersService loadServerUsers;
    private final SteamDealsService steamDealsService;

    public SlashCommandListener(PingService pingService, TestService testService, LoadGuildMembersService loadServerUsers, SteamDealsService steamDealsService ) {
        this.pingService = pingService;
        this.testService = testService;
        this.loadServerUsers = loadServerUsers;
        this.steamDealsService = steamDealsService;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    	String name = event.getName();

        switch (name) {
        case "ping":
        	String pingResponse = pingService.getPingResponse();
        	event.reply(pingResponse).queue();
        	break;
        case "load_deals":       	
        	event.reply("Descontos carregados no db").queue();
        	steamDealsService.loadDeals();       	        	
        	break;
        case "test":       	
        	event.reply("Teste123").queue();
        	loadServerUsers.loadUsers(event);       	        	
        	break;
        case "get_deals":
        	event.reply("Obtendo os 10 melhores descontos da Steam:").queue();       	
        	List<MessageEmbed> embeds = steamDealsService.buildDealsEmbed(1,0,30,true,"BR");       	      	  
        	
        	int batchSize = 10;

        	for (int i = 0; i < embeds.size(); i += batchSize) {
        	    int end = Math.min(i + batchSize, embeds.size());
        	    event.getChannel().sendMessageEmbeds(embeds.subList(i, end)).queue();
        	}
        	break;
        }
    }


}
package com.wlmd.discord_bot.listeners;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class GuildMessageEventListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent event) {
		String memberNickname = event.getMember().getNickname();
		String authorEffectiveName = event.getAuthor().getEffectiveName();
		String authorGlobalName = event.getAuthor().getGlobalName();
		String authorName = event.getAuthor().getName();
		String messageContentDisplay = event.getMessage().getContentDisplay();
		String messageContentRaw = event.getMessage().getContentRaw();
		String messageContentStripped = event.getMessage().getContentStripped();
		
		System.out.println("---------------------");
		System.out.println("Member Nickname: " + memberNickname);
		System.out.println("Author Effective Name: " + authorEffectiveName);
		System.out.println("Author Global Name: " + authorGlobalName);
		System.out.println("Author Name Teste 7: " + authorName);
		System.out.println("Message Content Display: " + messageContentDisplay);
		System.out.println("Message Content Raw: " + messageContentRaw);
		System.out.println("Message Content Stripped: " + messageContentStripped);
	} 

}

package com.wlmd.discord_bot.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlmd.discord_bot.dto.SteamDealsDto;
import com.wlmd.discord_bot.model.SteamDealsModel;
import com.wlmd.discord_bot.repository.SteamDealsRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

@Service
public class SteamDealsService {

	private final RestClient restclient;
	private final SteamDealsRepository steamDealsRepository;



	public SteamDealsService(RestClient restclient, SteamDealsRepository steamDealsRepository) {

		this.restclient = restclient;
		this.steamDealsRepository = steamDealsRepository;
	}

	// Prototype using the steam store api to get the BR prices
	// Used only for tests
	public List<MessageEmbed>  getDeals5() {
		List<MessageEmbed> embeds = new ArrayList<>();
		boolean haveResult = true;

		for(int i = 0; i<10; i++) {

			List<SteamDealsDto> deals = restclient.get()
					.uri("https://www.cheapshark.com/api/1.0/deals?storeID=1&AAA=1&onSale=1&pageSize=60&pageNumber={i}", i)
					.accept(APPLICATION_JSON)
					.retrieve()
					.body(new ParameterizedTypeReference<List<SteamDealsDto>>() {});

			System.out.println("TAMANHO LISTA: " + deals.size());
			if(deals == null || deals.isEmpty()) {
				break;
			}

			for(SteamDealsDto deal : deals) {

				EmbedBuilder embed = new EmbedBuilder();
				String appId = deal.getSteamAppID();

				if (appId == null || appId.isBlank()) {
					continue; 
				}

				Map<String, Object> result = restclient.get()
						.uri("https://store.steampowered.com/api/appdetails?appids={appId}&cc=BR", appId)
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
						.accept(APPLICATION_JSON)
						.retrieve()
						.body(new ParameterizedTypeReference<>() {});

				Map<String, Object> appData = (Map<String, Object>) result.get(appId);

				if (appData != null && Boolean.TRUE.equals(appData.get("success"))) {

					Map<String, Object> data = (Map<String, Object>) appData.get("data");					
					Map<String, Object> priceOverview = (Map<String, Object>) data.get("price_overview");

					String name = (String) data.get("name");
					String fullPrice = "N/A";
					String salePrice = "N/A";

					if (priceOverview != null) {
						fullPrice = (String) priceOverview.getOrDefault("initial_formatted", "N/A");
						salePrice = (String) priceOverview.getOrDefault("final_formatted", "N/A");
					}

					String url = "https://store.steampowered.com/app/" + appId + "/";
					deal.setBrNormalPrice(fullPrice);
					deal.setBrSalePrice(salePrice);
					deal.setSteamUrl(url);
				}

				String normalPrice = deal.getBrNormalPrice();
				String salePrice = deal.getBrSalePrice();
				if (normalPrice == null) normalPrice = "N/A";
				if (salePrice == null) salePrice = "N/A";
				String title = deal.getTitle();
				String url = deal.getSteamUrl();
				String description = String.format("""
						🎮 **[%s na Steam (Abrir no navegador)](%s)**  
						""", title, url);	
				embed.setTitle(title);
				embed.setUrl(url);
				embed.setUrl(deal.getSteamUrl());
				embed.setImage(deal.getThumb());
				//embed.setDescription(description);
				embed.addField("Valor original", normalPrice, true);
				embed.addField("Promoção", salePrice, true);
				MessageEmbed messageEmbed = embed.build();
				embeds.add(messageEmbed);
			}
		}
		return embeds;
	}

	// Load steam deals from cheapshark api to DB
	// TODO: Handle exceptons and make more robust
	public void  loadDeals() {
		steamDealsRepository.deleteAllInBatch();
		for(int i = 0; i<1000; i++) {

			List<SteamDealsModel> deals = restclient.get()
					.uri("https://www.cheapshark.com/api/1.0/deals?storeID=1,3&AAA=1&onSale=1&pageSize=60&pageNumber={i}", i)
					.accept(APPLICATION_JSON)
					.retrieve()
					.body(new ParameterizedTypeReference<List<SteamDealsModel>>() {});
			if(deals == null || deals.isEmpty()) {
				break;
			}else {
				
				steamDealsRepository.saveAll(deals);
			}
		}
		System.out.println("All deals updated!");
	}

	public List<SteamDealsModel> changeDealsCurrency(List<SteamDealsModel> deals, String currency){

		String currencyToChange = currency;
		for(SteamDealsModel deal : deals) {
			System.out.println("LOG ANTES CONVERTER PRECO ORIGINAL: " + deal.getTitle() + " NORMAL PRICE: " + deal.getNormalPrice() + " SALE PRICE: " + deal.getSalePrice());
			System.out.println("LOG ANTES CONVERTER PRECO BR: " + deal.getTitle() + " BR NORMAL PRICE: " + deal.getChangedNormalPrice() + " BR SALE PRICE: " + deal.getChangedSalePrice());
			String appId = deal.getSteamAppID();

			if (appId == null || appId.isBlank()) {
				continue; 
			}

			Map<String, Object> result = restclient.get()
					.uri("https://store.steampowered.com/api/appdetails?appids={appId}&cc={currencyToChange}", appId, currencyToChange)
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
					.accept(APPLICATION_JSON)
					.retrieve()
					.body(new ParameterizedTypeReference<>() {});

			Map<String, Object> appData = (Map<String, Object>) result.get(appId);

			if (appData != null && Boolean.TRUE.equals(appData.get("success"))) {

				Map<String, Object> data = (Map<String, Object>) appData.get("data");					
				Map<String, Object> priceOverview = (Map<String, Object>) data.get("price_overview");

				String name = (String) data.get("name");
				String fullPrice = "N/A";
				String salePrice = "N/A";
				String discountPercent = "N/A";

				if (priceOverview != null) {
					fullPrice = priceOverview.getOrDefault("initial_formatted", "N/A").toString();
					salePrice =  priceOverview.getOrDefault("final_formatted", "N/A").toString();
					discountPercent =  priceOverview.getOrDefault("discount_percent", "N/A").toString();
				}

				deal.setChangedNormalPrice(fullPrice);
				deal.setChangedSalePrice(salePrice);
				deal.setChangedSavings(discountPercent);
			}

		}

		return deals;
	}


	// Build the deals embed for discord (storeId 1 for steam and 3 for green man gaming)
	// TODO: Handle exceptons and make more robust
	public List<MessageEmbed> buildDealsEmbed(int storeID, int offset, int Limit, boolean changeCurrency, String currency) {
		List<MessageEmbed> embeds = new ArrayList<>();
		List<SteamDealsModel> deals = steamDealsRepository.findTopDealsByStoreId(1, PageRequest.of(offset, Limit));

		if(changeCurrency == true) {
			deals = changeDealsCurrency(deals, currency);
		}

		for(SteamDealsModel deal : deals) {

			EmbedBuilder embed = new EmbedBuilder();
			String title = deal.getTitle();
			String url = deal.getSteamUrl();			
			if (url == null) url = "N/A";
			embed.setTitle(title);
			embed.setUrl(url);
			embed.setImage(deal.getThumb());

			if (deal.getChangedNormalPrice() != null && deal.getChangedSalePrice() !=null && !deal.getChangedNormalPrice().isEmpty() && !deal.getChangedSalePrice().isEmpty() && changeCurrency == true) {
				String fullPrice = deal.getChangedNormalPrice();
				String salePrice = deal.getChangedSalePrice();
				embed.addField("Original price", fullPrice, true);
				embed.addField("Sale price", salePrice, true);
				embed.addField("Desconto de ", deal.getChangedSavings() + "%", true);
			}else {
				embed.addField("Desconto de ()", deal.getSavings() + "%", true);
			}

			
			System.out.println("LOG APOS CONVERTER: " + deal.getTitle() + " BR NORMAL PRICE: " + deal.getChangedNormalPrice() + " BR SALE PRICE: " + deal.getChangedSalePrice());
			MessageEmbed messageEmbed = embed.build();
			embeds.add(messageEmbed);
		}
		return embeds;
	}
}

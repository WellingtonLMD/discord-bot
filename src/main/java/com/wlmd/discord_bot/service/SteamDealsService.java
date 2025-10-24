package com.wlmd.discord_bot.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SteamDealsService {
	
	private final RestClient restclient;
	private final ObjectMapper objectMapper;
	
	
	public SteamDealsService(RestClient restclient, ObjectMapper objectMapper) {
		super();
		this.restclient = restclient;
		this.objectMapper = objectMapper;
	}

	
	public void getDeals() {
		String result = restclient.get()
				.uri("https://www.cheapshark.com/api/1.0/deals?storeID=1&AAA=1&pageSize=60&pageNumber=0")
				.retrieve()
				.body(String.class);
		
		System.out.println("RESULTADO: " + result);
		System.out.println();
	}
	
	public void getDeals2() {
		String result = restclient.get()
				.uri("https://www.cheapshark.com/api/1.0/deals?storeID=1&AAA=1&pageSize=60&pageNumber=0")
				.retrieve()
				.body(new ParameterizedTypeReference<>() {});
		

        JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(result);
			JsonNode dealsNode = rootNode;
			
			for(JsonNode elementNode : dealsNode) {
				System.out.println("Nome do jogo: " + elementNode.get("title"));
			}
			
			System.out.println();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

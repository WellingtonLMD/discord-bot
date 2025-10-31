package com.wlmd.discord_bot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class SteamDealsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(nullable = true)
	private String internalName;
    
    @Column(nullable = true)
	private String title;
	
    @Column(nullable = true)
	private String metacriticLink;
	
    @Column(nullable = true)
	private String dealID;
	
    @Column(nullable = true)
	private String storeID;
	
    @Column(nullable = true)
	private String gameID;
	
    @Column(nullable = true)
	private String salePrice;
	
    @Column(nullable = true)
	private String changedSalePrice;
	
    @Column(nullable = true)
	private String normalPrice;
	
    @Column(nullable = true)
	private String changedNormalPrice;
	
	@Column(nullable = true)
	private String isOnSale;
	
	@Column(nullable = true)
	private String savings;
	
	@Column(nullable = true)
	private String changedSavings;
	
	@Column(nullable = true)
	private String metacriticScore;
	
	@Column(nullable = true)
	private String steamRatingText;
	
	@Column(nullable = true)
	private String steamRatingPercent;
	
	@Column(nullable = true)
	private String steamAppID;
	
	@Column(nullable = true)
	private String releaseDate;
	
	@Column(nullable = true)
	private String lastChange;
	
	@Column(nullable = true)
	private String dealRating;
	
	@Column(nullable = true)
	private String thumb;
	
	public SteamDealsModel() {};
	
	public SteamDealsModel(Long id ,String internalName, String title, String metacriticLink, String dealID, String storeID,
			String gameID, String salePrice, String changedSalePrice, String normalPrice, String changedNormalPrice,
			String isOnSale, String savings, String metacriticScore, String steamRatingText, String steamRatingPercent,
			String steamAppID, String releaseDate, String lastChange, String dealRating, String thumb, String changedSavings) {
		
		this.id = id;
		this.internalName = internalName;
		this.title = title;
		this.metacriticLink = metacriticLink;
		this.dealID = dealID;
		this.storeID = storeID;
		this.gameID = gameID;
		this.salePrice = salePrice;
		this.changedSalePrice = changedSalePrice;
		this.normalPrice = normalPrice;
		this.changedNormalPrice = changedNormalPrice;
		this.isOnSale = isOnSale;
		this.savings = savings;
		this.changedSavings = changedSavings;
		this.metacriticScore = metacriticScore;
		this.steamRatingText = steamRatingText;
		this.steamRatingPercent = steamRatingPercent;
		this.steamAppID = steamAppID;
		this.releaseDate = releaseDate;
		this.lastChange = lastChange;
		this.dealRating = dealRating;
		this.thumb = thumb;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetacriticLink() {
		return metacriticLink;
	}

	public void setMetacriticLink(String metacriticLink) {
		this.metacriticLink = metacriticLink;
	}

	public String getDealID() {
		return dealID;
	}

	public void setDealID(String dealID) {
		this.dealID = dealID;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getChangedSalePrice() {
		return changedSalePrice;
	}

	public void setChangedSalePrice(String changedSalePrice) {
		this.changedSalePrice = changedSalePrice;
	}

	public String getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}

	public String getChangedNormalPrice() {
		return changedNormalPrice;
	}

	public void setChangedNormalPrice(String changedNormalPrice) {
		this.changedNormalPrice = changedNormalPrice;
	}

	public String getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(String isOnSale) {
		this.isOnSale = isOnSale;
	}

	public String getSavings() {
		return savings;
	}

	public void setSavings(String savings) {
		this.savings = savings;
	}

	public String getMetacriticScore() {
		return metacriticScore;
	}

	public void setMetacriticScore(String metacriticScore) {
		this.metacriticScore = metacriticScore;
	}

	public String getSteamRatingText() {
		return steamRatingText;
	}

	public void setSteamRatingText(String steamRatingText) {
		this.steamRatingText = steamRatingText;
	}

	public String getSteamRatingPercent() {
		return steamRatingPercent;
	}

	public void setSteamRatingPercent(String steamRatingPercent) {
		this.steamRatingPercent = steamRatingPercent;
	}

	public String getSteamAppID() {
		return steamAppID;
	}

	public void setSteamAppID(String steamAppID) {
		this.steamAppID = steamAppID;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public String getDealRating() {
		return dealRating;
	}

	public void setDealRating(String dealRating) {
		this.dealRating = dealRating;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getSteamUrl() {
		return "https://store.steampowered.com/app/" + steamAppID  + "/";
	}

	public String getChangedSavings() {
		return changedSavings;
	}

	public void setChangedSavings(String changedSavings) {
		this.changedSavings = changedSavings;
	}
	
}

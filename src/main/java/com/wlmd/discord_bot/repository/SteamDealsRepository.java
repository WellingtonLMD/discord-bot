package com.wlmd.discord_bot.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wlmd.discord_bot.model.SteamDealsModel;

import jakarta.transaction.Transactional;

public interface SteamDealsRepository extends JpaRepository <SteamDealsModel, Integer> {
	@Query(value = """
		SELECT *
		FROM public.steam_deals_model
		WHERE CAST(storeid AS NUMERIC) = :storeId
		ORDER BY CAST(metacritic_score AS NUMERIC) DESC	
			""",nativeQuery = true)
	List<SteamDealsModel> findTopDealsByStoreId(@Param("storeId") int storeId, Pageable pageanle);
	
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE steam_deals_model RESTART IDENTITY CASCADE", nativeQuery = true)
	void truncateTable();
}

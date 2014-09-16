package com.andriyprokip.sombrashop.manager.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import com.andriyprokip.sombrashop.entity.Deal;
import com.andriyprokip.sombrashop.entity.Lot;

@Component
public interface IDealManager {
	
	public List<Deal> findLastViewedLotsByUser(String login);

	void addDeal(String login, Lot lot);

	void deleteDeal(String login, int lotId);

	void deleteHistoriesByUser(String login);
	
}

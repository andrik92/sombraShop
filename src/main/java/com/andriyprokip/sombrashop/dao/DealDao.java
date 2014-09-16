package com.andriyprokip.sombrashop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Deal;

@Repository
public interface DealDao extends JpaRepository<Deal, Integer> {

	public List<Deal> findLastViewedLotsByUser(String login);

	Deal findDealByUserAndLot(String login, int lotId);
}

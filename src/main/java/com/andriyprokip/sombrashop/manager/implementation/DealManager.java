package com.andriyprokip.sombrashop.manager.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.DealDao;
import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.Deal;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.IDealManager;

@Service
public class DealManager implements IDealManager {

	@Autowired
	private DealDao dealDao;

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Deal> findLastViewedLotsByUser(String login) {
		return dealDao.findLastViewedLotsByUser(login);
	}
	
	@Override
	@Transactional
	public void addDeal(String login, Lot lot) {
		User user = userDao.findByLogin(login);
		dealDao.save(new Deal(user, lot));
	}

	@Override
	public void deleteDeal(String login, int lotId) {
		dealDao.delete(dealDao.findDealByUserAndLot(login, lotId));
	}
	
	/**
	 * Delete all deal of current user.
	 */
	@Override
	public void deleteHistoriesByUser(String login) {
		dealDao.delete(dealDao.findLastViewedLotsByUser(login));
	}

}

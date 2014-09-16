package com.andriyprokip.sombrashop.manager.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.CartDao;
import com.andriyprokip.sombrashop.dao.LotDao;
import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.Cart;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.ICartManager;

@Service
public class CartManager implements ICartManager {

	@Autowired
	CartDao cartDao;
	@Autowired
	LotDao lotDao;
	@Autowired
	UserDao userDao;

	@Override
	public void addToWatchlist(String login, Integer lotId) {
		Lot lot = lotDao.findOne(lotId);
		User user = userDao.findByLogin(login);
		cartDao.save(new Cart(user, lot));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<Cart> findAllCart(Integer pageNumber,
			Integer countItems, String sorterItem) {
		PageRequest request = new PageRequest(pageNumber - 1, countItems,
				Direction.DESC, sorterItem);
		return cartDao.findAll(request);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<User> findCartUserForLot(Integer lotId) {
		return cartDao.findCartUserForLot(lotId);
	}

	@Override
	public Page<Lot> findAllCart(String login, Integer pageNumber,
			Integer countItems, String sorterItem, String direction) {
		Direction usedDirection = Sort.Direction.fromString(direction);
		PageRequest request = new PageRequest(pageNumber - 1, countItems, usedDirection ,
				sorterItem);
		return cartDao.queryFindAllCartByUser(login, request);
	}
	
	@Override
	public void deleteById(int cartId) {
		cartDao.delete(cartId);

	}

	@Override
	public void deleteCart(String login, int lotId) {
		cartDao.delete(cartDao.findCartByUserAndLot(login, lotId));
	}

	@Override
	public boolean isInWatchlist(String login, Integer lotId) {
		Cart cart;
		cart = cartDao.findCartByUserAndLot(login, lotId);
		return cart == null ? false : true;
	}

}

package com.andriyprokip.sombrashop.manager.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.andriyprokip.sombrashop.entity.Cart;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.User;

public interface ICartManager {

	public void addToWatchlist(String login, Integer lotId);

	public Page<Cart> findAllCart(Integer pageNumber,
			Integer countItems, String sorterItem);

	public List<User> findCartUserForLot(Integer lotId);

	Page<Lot> findAllCart(String login, Integer pageNumber,
						Integer countItems, String sorterItem, String direction);

	public void deleteById(int cartId);

	public void deleteCart(String login, int lotId);
	
	public boolean isInWatchlist(String login, Integer lotId);
}

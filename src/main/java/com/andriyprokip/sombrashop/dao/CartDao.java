package com.andriyprokip.sombrashop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Cart;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.User;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
	public List<User> findCartUserForLot(Integer lotId);

	@Query(name = "query", value = "select l from Cart w JOIN w.buyer b JOIN w.lot l where b.login = ?1")
	Page<Lot> queryFindAllCartByUser(String login, Pageable pageable);
	
	Cart findCartByUserAndLot(String login, int lotId);
}

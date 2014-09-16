package com.andriyprokip.sombrashop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
@NamedQueries({
		@NamedQuery(name = "Cart.findCartUserForLot", query = "select b "
				+ "from Cart w JOIN w.buyer b JOIN w.lot l where l.lotId = ?1"),
		@NamedQuery(name = "Cart.findCartByUserAndLot", query = "select w from Cart w where w.buyer.login = ?1 AND w.lot.lotId = ?2") })

public class Cart extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CART_ID")
	private int cartId;

	@ManyToOne
	@JoinColumn(name = "BUYER")
	private User buyer;

	@ManyToOne
	@JoinColumn(name = "LOT")
	private Lot lot;

	public Cart() {
	}

	public Cart(User buyer, Lot lot) {
		this.buyer = buyer;
		this.lot = lot;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int bidId) {
		this.cartId = bidId;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}
}
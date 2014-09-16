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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "Deal.findDealByUserAndLot", query = "SELECT u FROM Deal u WHERE u.user.login = ?1 AND u.lot.lotId = ?2"),
		@NamedQuery(name = "Deal.findLastViewedLotsByUser", query = "SELECT u FROM Deal u WHERE u.user.login = ?1 ORDER BY u.date DESC") })

@Table(name = "DEAL")
public class Deal extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEAL_ID")
	private int dealId;

	@OneToOne
	@JoinColumn(name = "LOT_ID")
	private Lot lot;

	@ManyToOne
	@JoinColumn(name = "User_ID")
	private User user;

	private Deal() {
	}

	public Deal(User user, Lot lot) {
		this.lot = lot;
		this.user = user;
	}
	
	public int getDealId() {
		return dealId;
	}

	private void setDealId(int dealId) {
		this.dealId = dealId;
	}
	
	public Lot getLot() {
		return lot;
	}

	private void setLot(Lot lot) {
		this.lot = lot;
	}

	public User getUser() {
		return user;
	}

	private void setUser(User user) {
		this.user = user;
	}

}
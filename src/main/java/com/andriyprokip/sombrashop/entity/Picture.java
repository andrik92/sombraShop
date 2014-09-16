package com.andriyprokip.sombrashop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@NamedQueries({
		@NamedQuery(name = "Picture.findPicturesByLot", query = "SELECT p FROM Picture p WHERE p.lot.lotId =?1"),
		@NamedQuery(name = "Picture.deletePictureById", query = "DELETE FROM Picture p WHERE p.pictureId =?1") })
public class Picture extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PICTURE_ID")
	private int pictureId;

	@Lob
	@Column(name = "PICTURE")
	private byte[] picture;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	@JsonBackReference
	private Lot lot;

	@Column(name = "ISMAIN")
	private boolean main;

	public Picture() {

	}

	public Picture(byte[] picture) {
		this.picture = picture;
	}

	public Picture(byte[] picture, Lot lot) {
		this.picture = picture;
		this.lot = lot;
	}

	public int getPictureId() {
		return pictureId;
	}

	private void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Lot getLot() {
		return lot;
	}

	void setLot(Lot lot) {
		this.lot = lot;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean isMain) {
		this.main = isMain;
	}
	
}

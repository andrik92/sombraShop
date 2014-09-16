package com.andriyprokip.sombrashop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedQueries({

		@NamedQuery(name = "Lot.findLotByName", query = "SELECT l FROM Lot l WHERE l.lotName = ?1"),

		@NamedQuery(name = "Lot.findAvailableLots", query = "SELECT l FROM Lot l WHERE l.lotStatus = 'AVAILABLE'"),

		@NamedQuery(name = "Lot.findOutOfStockLots", query = "SELECT l FROM Lot l WHERE l.lotStatus = 'OUT_OF_STOCK'"),

		@NamedQuery(name = "Lot.findAvailableLotsByUser", query = "SELECT l FROM Lot l WHERE l.seller.userId =?1 AND l.lotStatus = 'AVAILABLE'"),

		@NamedQuery(name = "Lot.changecategory", query = "update Lot as u set u.category =:newcategory where u.category =:oldcategory"),

		@NamedQuery(name = "Lot.findOutOfStockLotsByUser", query = "SELECT l FROM Lot l WHERE l.seller.userId =?1 AND l.lotStatus = 'OUT_OF_STOCK'"),

		@NamedQuery(name = "Lot.findOutOfStockLotsBySeller", query = "SELECT l FROM Lot l WHERE l.seller.login =?1 AND l.lotStatus = 'OUT_OF_STOCK' "),

		@NamedQuery(name = "Lot.findLotsByCategory", query = "SELECT l FROM Lot l JOIN l.category c WHERE c.categoryName = :name"),

		@NamedQuery(name = "Lot.findLotsByCity", query = "SELECT l FROM Lot l JOIN l.city c WHERE c.cityName = :name"),

		@NamedQuery(name = "Lot.findLotsByPrice", query = "SELECT l FROM Lot l WHERE l.price BETWEEN ?1 AND ?2"),

		@NamedQuery(name = "Lot.findAvailableLotsByCategory", query = "SELECT l FROM Lot l WHERE l.lotStatus = 'AVAILABLE' and l.category = :catLot"),

		@NamedQuery(name = "Lot.findAvailableLotsByName", query = "SELECT l FROM Lot l "
				+ "WHERE l.lotName like :name and l.lotStatus = 'AVAILABLE'"),

		@NamedQuery(name = "Lot.findLotsByNameAndCategory", query = "SELECT l FROM Lot l "
				+ "WHERE l.category = :category AND l.lotName like :name and l.lotStatus = 'AVAILABLE'"),

		@NamedQuery(name = "Lot.getCitiesOfLots", query = "SELECT distinct l.city FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' AND "
				+ "l.category in :categories order by l.city.cityName"),

		@NamedQuery(name = "Lot.getCitiesOfLotsWithPrice", query = "SELECT distinct l.city FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice "
				+ "order by l.city.cityName"),

		@NamedQuery(name = "Lot.getCitiesOfLotsSubname", query = "SELECT distinct l.city FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories "
				+ "AND l.lotName like :name " + "order by l.city.cityName"),

		@NamedQuery(name = "Lot.getCitiesOfLotsWithPriceSubname", query = "SELECT distinct l.city FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice "
				+ "order by l.city.cityName"),

		@NamedQuery(name = "Lot.getMinPriceOfLots", query = "SELECT MIN(l.price) FROM Lot l WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories"),

		@NamedQuery(name = "Lot.getMaxPriceOfLots", query = "SELECT MAX(l.price) FROM Lot l WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories"),

		@NamedQuery(name = "Lot.getMinPriceOfLotsFilter", query = "SELECT MIN(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' AND l.category in (:categories) "
				+ "AND (l.city in :cities) "
				+ "AND l.price >= :minPrice AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMaxPriceOfLotsFilter", query = "SELECT MAX(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' AND l.category in (:categories) "
				+ "AND (l.city in :cities) "
				+ "AND l.price >= :minPrice AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMinPriceOfLotsFilterWithoutCities", query = "SELECT MIN(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' AND l.category in (:categories) "
				+ "AND l.price >= :minPrice AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMaxPriceOfLotsFilterWithoutCities", query = "SELECT MAX(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' AND l.category in (:categories) "
				+ "AND l.price >= :minPrice AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMinPriceOfLotsFilterWithoutCitiesSubname", query = "SELECT MIN(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in (:categories) "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMaxPriceOfLotsFilterWithoutCitiesSubname", query = "SELECT MAX(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in (:categories) "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMinPriceOfLotsSubname", query = "SELECT MIN(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in (:categories) "
				+ "AND l.lotName like :name "),

		@NamedQuery(name = "Lot.getMaxPriceOfLotsSubname", query = "SELECT MAX(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in (:categories) "
				+ "AND l.lotName like :name "),

		@NamedQuery(name = "Lot.getMinPriceOfLotsFilterWithCitiesSubname", query = "SELECT MIN(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in (:categories) "
				+ "AND l.city in (:cities) "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getMaxPriceOfLotsFilterWithCitiesSubname", query = "SELECT MAX(l.price) FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in (:categories) "
				+ "AND l.city in (:cities) "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getCategoriesOfLotsWithSubName", query = "SELECT distinct l.category FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories "
				+ "AND l.lotName like :name "),

		@NamedQuery(name = "Lot.getCategoriesOfLotsWithSubNamePrice", query = "SELECT distinct l.category FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in :categories "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getCategoriesOfLotsWithSubNamePriceCities", query = "SELECT distinct l.category FROM Lot l "
				+ "WHERE l.lotStatus = 'AVAILABLE' "
				+ "AND l.category in :categories "
				+ "AND l.city in (:cities) "
				+ "AND l.lotName like :name "
				+ "AND l.price >= :minPrice "
				+ "AND l.price <= :maxPrice"),

		@NamedQuery(name = "Lot.getAvailableLotByUserDateAndName", query = "SELECT l FROM Lot l"
				+ " WHERE l.lotStatus = 'AVAILABLE' AND l.lotName like :name  "
				+ "AND (l.seller.userId = :userId) "), })
@Table(name = "LOT")
public class Lot extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOT_ID")
	private Integer lotId;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private LotStatus lotStatus = LotStatus.AVAILABLE;

	@Column(name = "LOT_NAME")
	@Size(min = 3, message = "Name must be at least 3 characters!")
	private String lotName;

	@Lob
	@Column(name = "PICTURE")
	private String picture;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private Double price = 0.0;

	@ManyToOne
	@JoinColumn(name = "SELLER_ID")
	@JsonBackReference
	private User seller;

	@OneToMany(mappedBy = "lot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Picture> pictures = new HashSet<>();

	@NotNull(message = "Choose location")
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	@JsonBackReference
	private City city;

	@NotNull(message = "Choose category")
	@ManyToOne
	@JoinColumn(name = "CATEGORY")
	private Category category;

	public Lot() {

	}

	public Lot(Boolean trueValue) {
		lotStatus = LotStatus.AVAILABLE;
		lotName = "Without name";
		description = "Add description";
	}

	private Lot(LotBuilder builder) {
		this.lotStatus = builder.lotStatus;
		this.seller = builder.seller;
		this.lotName = builder.lotName;
		this.description = builder.description;
		this.picture = builder.picture;
		this.city = builder.city;
		this.category = builder.category;
	}

	public void addPicture(Picture picture) {
		picture.setLot(this);
		pictures.add(picture);
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public LotStatus getLotStatus() {
		return lotStatus;
	}

	public void setLotStatus(LotStatus lotStatus) {
		this.lotStatus = lotStatus;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getLotId() {
		return lotId;
	}

	@SuppressWarnings("unused")
	private void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public String getLotName() {
		return lotName;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public static class LotBuilder {
		private Lot lot;
		private final User seller;
		private final Double price;

		private LotStatus lotStatus = LotStatus.AVAILABLE;
		private String lotName = "Without name";
		private String description = "Add description";
		private String picture = null;

		private City city = null;
		private Category category = null;

		public LotBuilder(User seller, Double price) {
			this.seller = seller;
			this.price = price;
		}

		public LotBuilder lotName(String lotName) {
			this.lotName = lotName;
			return this;
		}

		public LotBuilder description(String description) {
			this.description = description;
			return this;
		}

		public LotBuilder picture(String picture) {
			this.picture = picture;
			return this;
		}

		public LotBuilder city(City city) {
			this.city = city;
			return this;
		}

		public LotBuilder category(Category category) {
			this.category = category;
			return this;
		}

		public Lot buildLot() {
			lot = new Lot(this);
			seller.getLotsSet().add(lot);
			return lot;

		}

	}

}

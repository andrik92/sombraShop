package com.andriyprokip.sombrashop.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.User;

@Repository
public interface LotDao extends JpaRepository<Lot, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Lot findOne(Integer id);

	public List<Lot> findLotsByCategory(String categoryName);

	public List<Lot> findAvailableLots();

	public List<Lot> findOutOfStockLots();

	public Page<Lot> findAvailableLotsByUser(int userId, Pageable pageable);

	public List<Lot> findLotsByCity(String cityName);

	public List<Lot> findLotsByPrice(double minPrice, double maxPrice);

	public List<Lot> findAvailableLotsByCategory(@Param("catLot") Category catLot);

	public Lot findLotByName(String lotName);

	public Page<Lot> findBySeller(User user, Pageable pageable); 

	@Modifying
	public void changecategory(@Param("newcategory") Category category,
			@Param("oldcategory") Category category1);

	public List<Lot> findAvailableLotsByName(@Param("name") String lotName);

	public List<Lot> findLotsByNameAndCategory(@Param("name") String name,
			@Param("category") Category category);

	@Query("SELECT l FROM Lot l WHERE l.lotStatus = 'AVAILABLE' AND l.category in :categories")
	public Page<Lot> lotOfCategories(
			@Param("categories") List<Category> categories, Pageable pageable);

	public Page<Lot> findAvailableLotsByName(@Param("name") String lotName,
			Pageable pageable);

	@Query("SELECT l FROM Lot l WHERE l.lotStatus = 'AVAILABLE' AND "
			+ "l.lotName like :name and l.category in :categories")
	public Page<Lot> findLotsByNameInCategories(@Param("name") String subName,
			@Param("categories") List<Category> listCategories,
			Pageable pageable);

	public List<City> getCitiesOfLots(
			@Param("categories") List<Category> listCategories);
	
	public List<City> getCitiesOfLotsSubname(
			@Param("name") String subName,
			@Param("categories") List<Category> categories);

	public Double getMinPriceOfLots(
			@Param("categories") List<Category> listCategories);

	public Double getMaxPriceOfLots(
			@Param("categories") List<Category> listCategories);

	@Query("SELECT l from Lot l WHERE l.lotStatus = 'AVAILABLE' AND "
			+ "l.category in (:categories) " 
			+ "AND (l.city in (:cities) )"
			+ "AND (l.price >= :minPrice) "
			+ "AND (l.price <= :maxPrice)")
	public Page<Lot> lotOfCategoriesFilter(
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice, Pageable pageable);

	public Double getMinPriceOfLotsFilter(
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMaxPriceOfLotsFilter(
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	@Query("SELECT l from Lot l WHERE l.lotStatus = 'AVAILABLE' AND "
			+ "l.category in (:categories) "
			+ "AND (l.price >= :minPrice) "
			+ "AND (l.price <= :maxPrice)")
	public Page<Lot> lotOfCategoriesFilter(
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice, Pageable pageable);

	public Double getMinPriceOfLotsFilterWithoutCities(
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMaxPriceOfLotsFilterWithoutCities(
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public List<City> getCitiesOfLotsWithPrice(
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public List<City> getCitiesOfLotsWithPriceSubname(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	@Query("SELECT l FROM Lot l WHERE l.lotStatus = 'AVAILABLE' AND "
			+ "l.lotName like :name " + "AND l.category in :categories "
			+ "AND l.price >= :minPrice "
			+ "AND l.price <= :maxPrice ")
	public Page<Lot> findLotsByNameInCategoriesFilter(
			@Param("name") String subName,
			@Param("categories") List<Category> categories, Pageable pageable,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMinPriceOfLotsFilterWithoutCitiesSubname(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMaxPriceOfLotsFilterWithoutCitiesSubname(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	@Query("SELECT l FROM Lot l WHERE l.lotStatus = 'AVAILABLE' "
			+ "AND l.lotName like :name " + "AND l.category in :categories "
			+ "AND l.city in (:cities) " + "AND l.price >= :minPrice "
			+ "AND l.price <= :maxPrice ")
	public Page<Lot> findLotsByNameInCategoriesCitiesFilter(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities, Pageable pageable,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMinPriceOfLotsFilterWithCitiesSubname(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMaxPriceOfLotsFilterWithCitiesSubname(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public Double getMinPriceOfLotsSubname(@Param("name") String subName,
			@Param("categories") List<Category> categories);

	public Double getMaxPriceOfLotsSubname(@Param("name") String subName,
			@Param("categories") List<Category> categories);

	public List<Category> getCategoriesOfLotsWithSubName(
			@Param("name") String subName,
			@Param("categories") List<Category> allSubCategories);

	public List<Category> getCategoriesOfLotsWithSubNamePriceCities(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("cities") List<City> cities,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	public List<Category> getCategoriesOfLotsWithSubNamePrice(
			@Param("name") String subName,
			@Param("categories") List<Category> categories,
			@Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);
	public Lot getAvailableLotByUserDateAndName(@Param("name") String lotName,
			@Param("userId") Integer userId);
}

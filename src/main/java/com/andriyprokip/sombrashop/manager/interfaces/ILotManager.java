package com.andriyprokip.sombrashop.manager.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.andriyprokip.sombrashop.entity.BeanForFilter;
import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.forms.SearchResult;

@Component
public interface ILotManager {

	public void save(List<Lot> lots);

	public void save(Lot lot);

	public Page<Lot> findByUser(Integer userId, int pageNumber, int pageSize,
			String sortingColumn);

	public List<Lot> findAvailableLots();

	public Page<Lot> findAvailableLotsByUser(Integer userId, int pageNumber,
			int pageSize, String sortingColumn);

	public void delete(List<Lot> lots);

	public List<Lot> findAll();

	public Lot find(Integer lotId);

	public Lot findLotByName(String lotName);

	public void changecategory(Category category, Category category1);
	
	public Lot getAvailableLotByUserDateAndName(String lotName,
			 Integer userId);

	/**
	 * The method returns all necessary parameters for selectLots.jsp when
	 * filter is used: list of select lots, page parameters etc.
	 * 
	 * @param filter
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param direction
	 * @return
	 */
	public SearchResult getResultOfSearch(BeanForFilter filter, int pageNumber,
			int pageSize, String sortBy, String direction);

	/**
	 * The method returns all necessary parameters for categorySelect.jsp when
	 * filter is used: list of select lots, page parameters etc.
	 * 
	 * @param filter
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param direction
	 * @return
	 */
	public SearchResult getResultOfSearchInCategory(BeanForFilter filter,
			int pageNumber, int pageSize, String sortBy, String direction);

}

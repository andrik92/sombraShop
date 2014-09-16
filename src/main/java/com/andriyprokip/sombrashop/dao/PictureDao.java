package com.andriyprokip.sombrashop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Picture;

@Repository
public interface PictureDao extends JpaRepository<Picture, Integer> {

	public List<Picture> findPicturesByLot(int lotId);
	
	public void deletePictureById(int pictureId);
}

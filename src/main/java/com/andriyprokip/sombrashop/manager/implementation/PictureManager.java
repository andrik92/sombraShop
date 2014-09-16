package com.andriyprokip.sombrashop.manager.implementation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.andriyprokip.sombrashop.dao.CategoryDao;
import com.andriyprokip.sombrashop.dao.LotDao;
import com.andriyprokip.sombrashop.dao.PictureDao;
import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.Picture;
import com.andriyprokip.sombrashop.manager.interfaces.IPictureManager;

@Service
public class PictureManager implements IPictureManager {

	@Autowired
	private PictureDao pictureDao;
	
	@Autowired
	private LotDao lotDao;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Picture> findByLot(int lotId) {

		return pictureDao.findPicturesByLot(lotId);
	}

	@Override
	@Transactional
	public void deleteById(int pictureId) {
		pictureDao.delete(pictureId);
	}
	
	@Override
	public void uploadLotImage( MultipartFile file, int lotId ) throws IOException{
		if (!file.isEmpty() && file.getContentType().startsWith("image/")) {
			byte[] imageInByte = file.getBytes();
			Lot lot = lotDao.findOne(lotId);
			Picture picture = new Picture(imageInByte);
			lot.addPicture(picture);
			lotDao.save(lot);
		}
	}
	
	public byte[] showLotImage(int lotId){
	Lot lot = lotDao.findOne(lotId);

	byte[] image = null;
	if (lot != null) {
		List<Picture> pictures = pictureDao.findPicturesByLot(lotId);
		if (pictures.size() != 0) {
			for (Picture picture : pictures) {

				image = picture.getPicture();
				break;
			}
		}
	}
	return image;
	}
	public static class SimplePicture{
		
		public Integer pictureId;
		public String image;

		public SimplePicture(Integer id, String image) {
			pictureId = id;
			this.image = image;
	
	}
}

}

package com.andriyprokip.sombrashop.manager.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.andriyprokip.sombrashop.entity.Picture;

public interface IPictureManager {

	public List<Picture> findByLot(int lotId);

	public void deleteById(int pictureId);
	
	public void uploadLotImage( MultipartFile file, int lotId ) throws IOException;
	
	public byte[] showLotImage(int lotId);
	
}

package com.andriyprokip.sombrashop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andriyprokip.sombrashop.entity.Picture;
import com.andriyprokip.sombrashop.manager.implementation.PictureManager;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;
import com.andriyprokip.sombrashop.manager.interfaces.IPictureManager;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;

@Controller
public class ImageController {
	
	@Autowired
	private ILotManager lotManager;
	@Autowired
	private IUserManager userManager;
	@Autowired
	private ICategoryManager categoryManager;
	@Autowired
	private IPictureManager pictureManager;

	@RequestMapping("/showLotImage/{lotId}")
	public @ResponseBody
	byte[] showImage(Model model, @PathVariable int lotId) {
	byte[] image =	pictureManager.showLotImage(lotId);
		return image;
	}

	@RequestMapping(value = "/showLotImages/{lotId}/", method = RequestMethod.GET)
	public @ResponseBody
	List<PictureManager.SimplePicture> showLotImagesJson(Model model,
			@PathVariable int lotId) {

		List<PictureManager.SimplePicture> models = new ArrayList<>();
		byte[] image = null;

		List<Picture> pictures = pictureManager.findByLot(lotId);

		String imageStr = null;
		Integer pictureId = null;

		if (pictures.size() != 0) {
			for (Picture picture : pictures) {
				pictureId = picture.getPictureId();
				image = picture.getPicture();
				imageStr = org.apache.commons.codec.binary.Base64
						.encodeBase64String(image);
				models.add(new PictureManager.SimplePicture(pictureId, imageStr));
			}
		}
		return models;
	}
}
package com.antunmod.pricetag.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.repo.ProductSpecificRepository;

/*
 *	 This is the Service class which handles photos.
 */
@Service
public class ImageService {

	@Autowired
	ProductSpecificRepository productSpecificRepository;
	
	private final String IMAGE_FOLDER_LOCATION = "C:/Users/antun/Documents/Projekti/PriceTag - Photos/";
	private final String FULL_SIZE_IMAGE = "original.jpg";
	private final String THUMBNAIL = "thumbnail.jpg";

	
	public Boolean saveImage(Byte[] imageArray, Short productSpecificId) {
		String newFolder = IMAGE_FOLDER_LOCATION + productSpecificId.toString();
		new File(newFolder).mkdirs();
		Path path = Paths.get(newFolder);
        Path fullSizeImagePath = Paths.get(path.toString() + "/" + FULL_SIZE_IMAGE);
        Path thumbnailPath = Paths.get(path.toString() + "/" + THUMBNAIL);
		
        
        
		try {

            
            
            byte[] image = new byte[imageArray.length];
            int i = 0;
            for (Byte b : imageArray)
            	image[i++] = b;
            
            Files.write(fullSizeImagePath, image);

        } catch (IOException e) {
            return false;
        }
		ProductSpecific productSpecific = productSpecificRepository.findById(productSpecificId);
		if (productSpecific != null) productSpecific.setPhotoURI(fullSizeImagePath.toString());
		productSpecificRepository.save(productSpecific);
		return true;
	}

}

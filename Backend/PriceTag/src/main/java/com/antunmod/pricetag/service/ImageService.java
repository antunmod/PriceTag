package com.antunmod.pricetag.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

/*
 *	 This is the Service class which handles photos.
 */
@Service
public class ImageService {

	private final String IMAGE_FOLDER_LOCATION = "C:/Users/antun/Documents/Projekti/PriceTag - Photos/";
	private final String FULL_SIZE_IMAGE = "original.jpg";
	private final String THUMBNAIL = "thumbnail.jpg";

	
	public Boolean saveImage(Byte[] imageArray, Short productSpecificId) {
		
		try {
            // Get the file and save it somewhere
            Path path = Paths.get(IMAGE_FOLDER_LOCATION + productSpecificId);
            Path fullSizeImagePath = Paths.get(path.toString() + FULL_SIZE_IMAGE);
            Path thumbnailPath = Paths.get(path.toString() + THUMBNAIL);
            
            byte[] image = new byte[imageArray.length];
            int i = 0;
            for (Byte b : imageArray)
            	image[i++] = b;
            
            Files.write(fullSizeImagePath, image);

        } catch (IOException e) {
            return false;
        }
		
		return true;
	}

}

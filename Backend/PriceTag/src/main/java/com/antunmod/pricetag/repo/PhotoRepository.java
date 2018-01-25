package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.antunmod.pricetag.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{

	@Query(value = "SELECT photo FROM photo WHERE photo_ID = ?1", nativeQuery=true)
	byte[] getPhotoForPhotoId(int photoId);
	
}

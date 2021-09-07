package com.example.demo.mapper.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.example.demo.entity.file.Files;

@Component
@Mapper
public interface FileMapper {
	Files findByGalleryId(@Param("galleryId") String galleryId);
	int insertFile(@Param("param") Files param);
	List<Files> selectAll();
	int deleteByGalleryId(@Param("galleryId") String galleryId);
	int updateByFile(@Param("param") Files file);
}

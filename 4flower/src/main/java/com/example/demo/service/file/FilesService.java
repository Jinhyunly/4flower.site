package com.example.demo.service.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.file.Files;
import com.example.demo.mapper.file.FileMapper;

@Service
public class FilesService {
	@Autowired
	FileMapper  fileMapper;

	// 사용 x
	public void save(Files files) {
		Files file = new Files();
		file.setGallery_id(files.getGallery_id());
		file.setGallery_fileName(files.getGallery_fileName());
		file.setGallery_fileOriName(files.getGallery_fileOriName());
		file.setGallery_url(files.getGallery_url());
		file.setEnt_kbn(files.getEnt_kbn());
		//fileMapper.findByGalleryId()
		//filesRepository.save(file);
	}

	public Files getFileById(String galleryId) {
		return fileMapper.findByGalleryId(galleryId);
	}

	public void saveFiles(Files files) {
		 fileMapper.insertFile(files);
	}

	public List<Files> selectAll() {
		return fileMapper.selectAll();
	}

	public void deleteFileById(String galleryId) {
		 fileMapper.deleteByGalleryId(galleryId);
	}
}
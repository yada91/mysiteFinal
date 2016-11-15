package com.bit2016.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.exception.UploadFileException;
import com.bit2016.mysite.repository.GalleryDAO;
import com.bit2016.mysite.vo.Gallery;

@Service
public class GalleryService {

	@Autowired
	private GalleryDAO galleryDAO;

	private static final String SAVE_PATH = "c:\\upload";
	private static final String URL = "/gallery/assets/";

	public String restore(MultipartFile file, String comments, Long userNo) {
		String url = "";
		try {
			if (file.isEmpty() == true) {
				return url;
			}

			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
			Long fileSize = file.getSize();

			Gallery vo = new Gallery();
			vo.setOrgFileName(orgFileName);
			vo.setSaveFileName(saveFileName);
			vo.setFileSize(fileSize);
			vo.setExtName(extName);
			vo.setComments(comments);
			vo.setUserNo(userNo);

			insert(vo);

			writeFile(file, saveFileName);
			url = URL + saveFileName;
		} catch (IOException e) {
			throw new UploadFileException("write file");
		}

		return url;
	}

	public List<Gallery> selectAll() {

		List<Gallery> list = galleryDAO.selectAll();

		return list;
	}

	public Gallery selectByNo(Long no) {

		Gallery vo = galleryDAO.selectByNo(no);

		return vo;
	}

	public void insert(Gallery vo) {
		galleryDAO.insert(vo);
	}

	public void delete(Gallery vo) {
		galleryDAO.delete(vo);
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += "." + extName;
		return fileName;
	}

	private void writeFile(MultipartFile file, String saveFileName) throws IOException {
		byte[] fileData = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(fileData);
		fos.close();
	}
}

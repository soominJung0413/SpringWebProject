package me.soomin.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@PostMapping("uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("update ajax post.......");
		String uploadFolder = "C:\\upload";

		for (MultipartFile multipartFile : uploadFile) {

			log.info("----------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);

			File file = new File(uploadFolder, uploadFileName);

			try {
				multipartFile.transferTo(file);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload Ajax");
	}

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form....");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) throws IOException {

		String uploadFolder = "C:\\upload";

		for (MultipartFile file : uploadFile) {
			log.info("----------------------------------------");
			log.info("Upload File Name : " + file.getOriginalFilename());
			log.info("Upload File Size : " + file.getSize());
			log.info("Upload File byte[] : " + file.getBytes());
			log.info("Upload Tag Name : " + file.getName());
			log.info("Upload File InputStream : " + file.getInputStream());

			File saveFile = new File(uploadFolder, file.getOriginalFilename());

			try {
				file.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

	}

}

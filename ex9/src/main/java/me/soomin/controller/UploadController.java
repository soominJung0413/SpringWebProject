package me.soomin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import me.soomin.domain.BoardAttachVO;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}

	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFIle(String fileName, String type) {
		log.info("deleteFile: " + fileName);

		File file;
		try {
			file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			if (type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");

				log.info("largeFileName: " + largeFileName);
				file = new File(largeFileName);

				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
		log.info("download file : " + fileName);

		Resource resource = new FileSystemResource("C:\\upload\\" + fileName);

		if (resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		log.info("resource : " + resource);

		String resourceName = resource.getFilename();

		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {
			String downloadName = null;

			if (userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			} else if (userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			} else {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}

			headers.add("Content-Disposition", "attachment; fileName=" + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName : " + fileName);

		File file = new File("C:\\upload\\" + fileName);

		log.info(file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@PostMapping(value = "/uploadAjaxAction", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<BoardAttachVO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload";

		String uploadFolderPath = getFolder();

		// make Folder -------
		File uploadPath = new File(uploadFolder, uploadFolderPath);// 날짜 폴더 까지 합쳐서 생성.
		log.info("upload Path : " + uploadPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		// make yyyy/MM/dd folder

		for (MultipartFile multipartFile : uploadFile) {
			BoardAttachVO dto = new BoardAttachVO();
			log.info("=========================");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			dto.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			log.info("only file Name : " + uploadFileName);

			File saveFile = new File(uploadPath, uploadFileName);

			try {
				multipartFile.transferTo(saveFile);
				dto.setUuid(uuid.toString());
				dto.setUploadPath(uploadFolderPath);

				// image File Check
				if (checkImageType(saveFile)) {

					dto.setFileType(true);
					FileOutputStream fos = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
					fos.close();
				}
				list.add(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<BoardAttachVO>>(list, HttpStatus.OK);
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

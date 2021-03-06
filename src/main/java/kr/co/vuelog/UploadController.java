package kr.co.vuelog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
@RequestMapping("/api")
public class UploadController {

	/*
	 * 파일 용량 산정 1kb = 1024byte, 1Mb = 1024kb, 10Mb = 10 * 1024 * 1024 byte
	 */

	private String uploadPath = "C:\\workspace\\sts_4.8.1RELEASE\\vuelog\\src\\main\\webapp\\resources\\fileUpload\\ckImage";

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	@PostMapping("/uploadFormAction")
	// MultipartFile[] uploadFile : uploadForm - form - input name과 일치해야 함
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			log.info("Upload File ContentType : " + multipartFile.getContentType());

			File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}

//	private String getFolder() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date date = new Date();
//		String str = sdf.format(date);
//
//		return str.replace("-", File.separator);
//	}

//	private boolean checkImageType(File file) {
//
//		try {
//			String contentType = Files.probeContentType(file.toPath());
//			log.info("contentType : " + contentType);
//			return contentType.startsWith("image");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}

//	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
//		log.info("update ajax post..........................");
//
//		List<AttachFileDTO> attachList = new ArrayList<AttachFileDTO>();
//
//		String uploadFolderPath = getFolder();
//
//		File uploadFolder = new File(uploadPath, getFolder());
//
//		log.info("uploadFolder path : " + uploadFolder);
//
//		if (uploadFolder.exists() == false) {
//			uploadFolder.mkdirs();
//		}
//
//		for (MultipartFile multipartFile : uploadFile) {
//			log.info("-----------------------------------------");
//			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//			log.info("Upload File Size : " + multipartFile.getSize());
//			log.info("Upload File ContentType : " + multipartFile.getContentType());
//
//			AttachFileDTO attachFileDTO = new AttachFileDTO();
//
//			String uploadFileName = multipartFile.getOriginalFilename();
//
//			// 익스플로러
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
//
//			log.info("only file name : " + uploadFileName);
//
//			attachFileDTO.setFileName(uploadFileName);
//
//			// 임의의 16자리 값을 가져옴
//			UUID uuid = UUID.randomUUID();
//
//			uploadFileName = uuid.toString() + "_" + uploadFileName;
//
//			try {
//				File savaFile = new File(uploadFolder, uploadFileName);
//				multipartFile.transferTo(savaFile);
//
//				attachFileDTO.setUuid(uuid.toString());
//				attachFileDTO.setUploadPath(uploadFolderPath);
//
//				if (checkImageType(savaFile)) {
//					attachFileDTO.setImage(true);
//
//					File thumbnail = new File(uploadFolder, "s_" + uploadFileName);
//					Thumbnails.of(savaFile).size(100, 100).toFile(thumbnail);
//				}
//				attachList.add(attachFileDTO);
//			} catch (Exception e) {
//				log.error(e.getMessage());
//			}
//
//		}
//
//		return new ResponseEntity<List<AttachFileDTO>>(attachList, HttpStatus.OK);
//	}

	@GetMapping("/display/{fileName}/")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
		log.info("fileName : " + fileName);

		File file = new File(uploadPath + "\\" + fileName);

		log.info("file : " + file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	@GetMapping("/download")
//	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
//		Resource resource = new FileSystemResource(uploadPath + "\\" + fileName);
//
//		if (resource.exists() == false) {
//			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
//		}
//
//		String resourceName = resource.getFilename();
//
//		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
//
//		HttpHeaders headers = new HttpHeaders();
//
//		try {
//			boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);
//
//			String downloadName = null;
//
//			if (checkIE) {
//				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", "");
//			} else {
//				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
//			}
//
//			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
//
//	}

	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("deleteFile : " + fileName);

		File file;

		try {
			file = new File(uploadPath + "\\" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			if (type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("\\s_", "\\");
				log.info("largeFileName : " + largeFileName);
				file = new File(largeFileName);
				// 경로를 못 찾아서 삭제 안 됨
				file.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	// ======================================== for
	// ckeditor=====================================
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	@ResponseBody
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws Exception {

		String uploadPathEditor = "C:\\workspace\\sts_4.8.1RELEASE\\vuelog\\src\\main\\webapp\\resources\\fileUpload";
		
		log.info("imageupload");
		
		// 랜덤 문자 생성
		UUID uuid = UUID.randomUUID();

		OutputStream out = null;
		PrintWriter printWriter = null;

		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String fileUrl = null;

		try {

			// 파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();

			// 이미지 경로 생성
			String path = uploadPathEditor + "/ckImage/";// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
			String ckUploadPath = path + uuid + "_" + fileName;

			File folder = new File(path);

			// 해당 디렉토리 확인
			if (!folder.exists()) {
				try {
					folder.mkdirs(); // 폴더 생성
				} catch (Exception e) {
					e.getStackTrace();
				}
			}

			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화

			// 리스트용 섬네일 생성
			
			File thumbnail = new File(path, "s_" + uuid + "_" + fileName);
			Thumbnails.of(new File(ckUploadPath)).size(500, 500).toFile(thumbnail);

			printWriter = response.getWriter();

			String contextPath = "http://localhost:8081"; // 내가 추가한 줄 뷰 빌드할때 꼭 스프링 포트로 바꿔주자
			fileUrl = contextPath + "/api/display/" + uuid + "_" + fileName + "/"; // 작성화면

//			fileUrl = contextPath + "/assets/logo.png";

			// 업로드시 메시지 출력
			printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return;
	}

	/**
	 * cKeditor 서버로 전송된 이미지 뿌려주기
	 * 
	 */
	//
//	@RequestMapping(value = "/mine/ckImgSubmit/{fileName}/")
//	public void ckSubmit(@PathVariable("fileName") String fileName,
//			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String uploadPathEditor = "C:\\workspace\\sts_4.8.1RELEASE\\vuelog\\src\\main\\webapp\\resources\\fileUpload\\ckImage";
//		// 서버에 저장된 이미지 경로
//
//		String sDirPath = uploadPathEditor + "\\" + fileName;
//		
//		log.info("filename : " +  fileName);
//
//		File imgFile = new File(sDirPath);
//
//		// 사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
//		if (imgFile.isFile()) {
//			byte[] buf = new byte[1024];
//			int readByte = 0;
//			int length = 0;
//			byte[] imgBuf = null;
//
//			FileInputStream fileInputStream = null;
//			ByteArrayOutputStream outputStream = null;
//			ServletOutputStream out = null;
//
//			try {
//				fileInputStream = new FileInputStream(imgFile);
//				outputStream = new ByteArrayOutputStream();
//				out = response.getOutputStream();
//
//				while ((readByte = fileInputStream.read(buf)) != -1) {
//					outputStream.write(buf, 0, readByte);
//				}
//
//				imgBuf = outputStream.toByteArray();
//				length = imgBuf.length;
//				out.write(imgBuf, 0, length);
//				out.flush();
//
//			} catch (IOException e) {
//				log.info(e);
//			} finally {
////	                outputStream.close;
////	                fileInputStream.close;
////	                out.close;
//			}
//		}
//	}

}

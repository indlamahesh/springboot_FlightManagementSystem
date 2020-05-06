package com.cg.flight.web;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.flight.exception.ImageException;
import com.cg.flight.util.MailConstants;

@RestController
public class UploadImageController {
	
	@Value("${imgpath}")
	private String imgPath;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("txtfile") MultipartFile file,
			@RequestParam("txtuid")String uid) throws IOException, ImageException {
	
		byte[] arr = file.getBytes();
		FileOutputStream fos = new FileOutputStream(imgPath+uid+MailConstants.IMG_TYPE);
				fos.write(arr);
		        fos.close();
		    return MailConstants.IMG_UPLOADED;
	}
}

package com.tour.project.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static Map<String, Object> uploadImage(MultipartFile file) throws Exception {
		String uuid = UUID.randomUUID().toString();
		//TODO 경로는 설정 후 테스트 진행
		String rootPath = "";
		String basePath = "";
		String copyDir = "";
		String filePath = "";
		
		File dest = new File(filePath);
		file.transferTo(dest);
		
		//TODO 수정
		String filePath2 = "";
		
		FileUtil.copyFile(filePath, filePath2);
		
		//TODO 수정
		String savePath = "";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("savePath", savePath);
		map.put("imageName", uuid+file.getOriginalFilename());
		
		return map;
		
	}
	
	public static void copyFile(String readFilePath, String copyFilepath) {
		File readFile = new File(readFilePath);
		File copyFile = new File(copyFilepath);
		
		try {
			FileInputStream fis = new FileInputStream(readFile);
			FileOutputStream fos  = new FileOutputStream(copyFile);
			
			int fileByte = 0;
			
			while ((fileByte = fis.read()) != -1) {
				fos.write(fileByte);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package kr.pe.playnote.com;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class DuplicateFile {
	public static File getFile(String uploadPath, MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String serverFileName = fileName;
        int cnt = 1;
        File file = null;
        do {
            file = new File(uploadPath, serverFileName);
            if (!file.exists())
            break;
            serverFileName = fileName.substring(0, fileName.lastIndexOf(".")) + (cnt++)
            + fileName.substring(fileName.lastIndexOf("."));
        } while (true);
        return file;
	}
}

package kr.pe.playnote.com;

import org.springframework.web.multipart.MultipartFile;

public class MultiFiles {
    
    private String title;
    private MultipartFile[] fileArry;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public MultipartFile[] getFileArry() {
        return fileArry;
    }
    public void setFileArry(MultipartFile[] fileArry) {
        this.fileArry = fileArry;
    }
}

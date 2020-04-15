package kr.pe.playnote.com.dto;

import java.util.Date;

public class AttachFileDto {
    
	private String uuid           ; //ID         
    private String boardUuid      ; //게시판 ID    
    private String orgFileName    ; //원본파일 이름  
    private String storedFileName ; //변경된 파일 이름
    private String fileDirectory  ; //저장 디렉토리  
    private int    fileSize       ; //파일크기      
    private Date   createDatetime ; //생성일       
    private String createUser     ; //생성자       
    private Date   updateDatetime ; //수정일       
    private String updateUser     ; //수정자
    
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getBoardUuid() {
		return boardUuid;
	}
	public void setBoardUuid(String boardUuid) {
		this.boardUuid = boardUuid;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getStoredFileName() {
		return storedFileName;
	}
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}
	public String getFileDirectory() {
		return fileDirectory;
	}
	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}

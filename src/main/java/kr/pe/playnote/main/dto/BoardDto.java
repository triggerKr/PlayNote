package kr.pe.playnote.main.dto;


public class BoardDto {

    private String uuid;            
    private String boardCode;       
    private String title;           
    private String userId;          
    private String subject;         
    private String contents;        
    private int    hits;            
    private String createDatetime;  
    private String createUser;      
    private String updateDatetime;  
    private String updateUser;
    private int rnum;
    private int    totalCount;

	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public BoardDto(String uuid,String boardCode,
            String title,String userId,
            String subject,String contents,
            int    hits,String createDatetime,
            String createUser,String updateDatetime,
            String updateUser) {
		this.uuid = uuid;          
		this.boardCode =boardCode;     
		this.title = title;         
		this.userId = userId;        
		this.subject = subject;       
		this.contents = contents;      
		this.hits = hits;          
		this.createDatetime = createDatetime;
		this.createUser = createUser;    
		this.updateDatetime = updateDatetime;
		this.updateUser = updateUser;    
		

	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalcount(int totalCount) {
		this.totalCount = totalCount;
	}
}
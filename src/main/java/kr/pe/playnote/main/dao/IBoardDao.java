package kr.pe.playnote.main.dao;

import java.util.List;

import kr.pe.playnote.main.dto.BoardDto;

public interface IBoardDao {
	public List<BoardDto> listDao();
	public List<BoardDto> pageListDao();
	public List<BoardDto> totalCountDao();
	public BoardDto noticeDetail();
	
	
	public List<BoardDto> getBoardListTest();
	public void writeDao(String mWriter, String mContent);
	public BoardDto viewDao(String strID);
	public void deleteDao(String bId);
}

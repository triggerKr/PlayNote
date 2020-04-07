package kr.pe.playnote.main.service;

import java.util.HashMap;
import java.util.List;

import kr.pe.playnote.main.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> getBoardList(HashMap<String, Object> paramMap);
	public List<BoardDto> getPageList(HashMap<String, Object> paramMap);
	public List<BoardDto> getTotalCount(HashMap<String, Object> paramMap);
	public BoardDto getNoticeDetail(HashMap<String, Object> paramMap);
	
	public List<BoardDto> getBoardListTest();
}

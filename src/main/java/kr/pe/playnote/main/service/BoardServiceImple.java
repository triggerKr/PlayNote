package kr.pe.playnote.main.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.playnote.main.dto.BoardDto;

@Service
public class BoardServiceImple implements BoardService{
	
	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.pe.playnote.main.dao.IBoardDao";
	
	@Override
    public List<BoardDto> getBoardList( HashMap<String, Object> paramMap ) {
		
		
		 String sqlId = ".listDao";
		 String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		 System.out.println("==== getBoardList ====");
	     System.out.println(sql);
	        
		 List<BoardDto> dto = sqlSession.selectList(namespace+sqlId, paramMap);
		 
        return dto;
    }
	
	@Override
    public List<BoardDto> getPageList( HashMap<String, Object> paramMap ) {
		
		
		 String sqlId = ".pageListDao";
		 String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		 System.out.println("==== getPageList ====");
	     System.out.println(sql);
	        
		 List<BoardDto> dto = sqlSession.selectList(namespace+sqlId, paramMap);
		 
        return dto;
    }

	@Override
    public List<BoardDto> getTotalCount(HashMap<String, Object> paramMap) {
		
		String sqlId = ".totalCountDao";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		System.out.println("==== getTotalCount ====");
	    System.out.println(sql);
	        
		 List<BoardDto> dto = sqlSession.selectList(namespace+sqlId, paramMap);
		 
       return dto;
    }

	@Override
    public BoardDto getNoticeDetail(HashMap<String, Object> paramMap) {
		
		String sqlId = ".noticeDetail";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		System.out.println("==== getNoticeDetail ====");
	    System.out.println(sql);
		 BoardDto dto = sqlSession.selectOne(namespace+sqlId, paramMap);
		 
       return dto;
    }
	
	@Override
    public List<BoardDto> getBoardListTest() {
		
		HashMap<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("BOARD_CODE", "1");
        String sqlId = ".listDao";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		List<BoardDto> dto = sqlSession.selectList(namespace+sqlId, paramMap);
        System.out.println("==== getBoardListTest getBoardListTest ====");
        System.out.println(sql);
        
        return dto;
    }
	
	



}
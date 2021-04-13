package kr.pe.playnote.com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.playnote.com.dto.MemberDto;

@Service
public class MemberServiceImple implements MemberService{

	@Autowired
	private SqlSession sqlSession;
	private String namespace = "kr.pe.playnote.com.dao.IMemberdDao";

	@Override
    public int memberSave(MemberDto dto) {
		
		String sqlId = ".memberSave";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(dto).getSql();
		System.out.println("==== memberSave ====");
	    System.out.println(sql);
	    int result = sqlSession.insert(namespace+sqlId, dto);
		 
       return result;
    }
	
	@Override
    public MemberDto emailDuplicateCheck(HashMap<String, Object> paramMap) {
		
		String sqlId = ".emailDuplicateCheck";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		System.out.println("==== emailDuplicateCheck ====");
	    System.out.println(sql);
	    MemberDto dto = sqlSession.selectOne(namespace+sqlId, paramMap);
		 
       return dto;
    }
	
	@Override
    public List<MemberDto> list( HashMap<String, Object> paramMap ) {
		
		
		 String sqlId = ".listDao";
		 String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		 System.out.println("==== list ====");
	     System.out.println(sql);
	        
		 List<MemberDto> dto = sqlSession.selectList(namespace+sqlId, paramMap);
		 
        return dto;
    }

	@Override
    public MemberDto detail(HashMap<String, Object> paramMap) {
		
		String sqlId = ".detail";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		System.out.println("==== detail ====");
	    System.out.println(sql);
	    MemberDto dto = sqlSession.selectOne(namespace+sqlId, paramMap);
		 
       return dto;
    }
	
	@Override
    public MemberDto loginChk(HashMap<String, Object> paramMap) {
		
		String sqlId = ".loginChk";
		String sql = sqlSession.getConfiguration().getMappedStatement(namespace+sqlId).getBoundSql(paramMap).getSql();
		System.out.println("==== loginChk ====");
	    System.out.println(sql);
	    MemberDto dto = sqlSession.selectOne(namespace+sqlId, paramMap);
		 
       return dto;
    }
}

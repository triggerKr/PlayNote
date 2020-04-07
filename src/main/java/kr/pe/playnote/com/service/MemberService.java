package kr.pe.playnote.com.service;

import java.util.HashMap;
import java.util.List;
import kr.pe.playnote.com.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> list(HashMap<String, Object> paramMap);
	public MemberDto detail(HashMap<String, Object> paramMap);
	public MemberDto loginChk(HashMap<String, Object> paramMap);
}

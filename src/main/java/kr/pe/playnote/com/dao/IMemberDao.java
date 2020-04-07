package kr.pe.playnote.com.dao;

import java.util.List;
import kr.pe.playnote.com.dto.MemberDto;

public interface IMemberDao {
	public List<MemberDto> list();
	public MemberDto detail();
	public MemberDto loginChk();
}

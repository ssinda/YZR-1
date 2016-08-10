package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.MemberVO;

public interface MemberDAO {
	
	public void insert(MemberVO vo);
	public MemberVO select(String member_id);
	public void update(String member_id);
	public void delete(String member_id);
	public boolean idCheck(String member_id);
	public boolean pwCheck(String member_pw, String member_id);
	
}

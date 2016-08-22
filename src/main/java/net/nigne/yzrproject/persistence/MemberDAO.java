package net.nigne.yzrproject.persistence;

import net.nigne.yzrproject.domain.MemberVO;

public interface MemberDAO {
	public MemberVO getMember(String member_id);
	
}

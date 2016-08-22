package net.nigne.yzrproject.persistence;

import net.nigne.yzrproject.domain.MemberVO;

public interface ClubDAO {
	public MemberVO getMember(String member_id);
	public void Member_club_Update(String member_id, String club) throws Exception;


}

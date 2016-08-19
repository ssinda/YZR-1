package net.nigne.yzrproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.persistence.ClubDAO;

@Service
public class ClubServiceImpl implements ClubService {
	
	@Autowired
	private ClubDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		return dao.getMember(member_id);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void Member_club_Update(String member_id, String club) throws Exception {
		// TODO Auto-generated method stub
		dao.Member_club_Update(member_id, club);
	}
	


}

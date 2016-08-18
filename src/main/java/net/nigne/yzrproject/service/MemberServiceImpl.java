package net.nigne.yzrproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;

	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		return dao.getMember(member_id);
	}
	
	

}

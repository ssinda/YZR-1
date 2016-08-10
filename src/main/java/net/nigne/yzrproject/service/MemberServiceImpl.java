package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.persistence.MemberDAO;

@Service // 서비스클래시 어노테이션 , 이것도 필수로 붙여주어야 하는건가?
public class MemberServiceImpl implements MemberService {

	
	@Autowired
	private MemberDAO dao;
	
	@Transactional(rollbackFor={Exception.class}) // 간단하게? 트랜잭션 처리!!
	@Override // 오버라이드
	public void insert(MemberVO vo) {
		
		dao.insert(vo);

	}
	@Transactional(readOnly=true) // 와이 true???
	@Override 
	public boolean idCheck(String member_id){
		
		return dao.idCheck(member_id);
	}
	
	@Transactional(readOnly=true)
	@Override 
	public boolean pwCheck(String member_pw, String member_id){
		
		return dao.pwCheck(member_pw, member_id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public MemberVO select(String member_id) {
		return dao.select(member_id);
	}
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void delete(String member_id) {
		try{
			dao.delete(member_id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

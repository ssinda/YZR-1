package net.nigne.yzrproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;
import net.nigne.yzrproject.persistence.AdminCouponDAO;
import net.nigne.yzrproject.persistence.AdminEtcDAO;
import net.nigne.yzrproject.persistence.AdminTimetableDAO;

@Service
public class AdminCouponServiceImpl implements AdminCouponService {

	@Autowired
	private AdminCouponDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<String> getMemberList() {
		// TODO Auto-generated method stub
		return dao.getMemberList();
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(CouponVO vo) {
		// TODO Auto-generated method stub
		dao.persist(vo);
	}
	@Transactional(readOnly=true)
	@Override
	public boolean checkId(String member_id) {
		// TODO Auto-generated method stub
		return dao.checkId(member_id);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persistClubCoupon(CouponVO vo){
		dao.persist(vo);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getClubMember(String club) {
		// TODO Auto-generated method stub
		return dao.getClubMember(club);
	}
}

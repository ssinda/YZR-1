package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.persistence.CouponDAO;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponDAO dao;

	@Override
	public List<CouponVO> getCouponList(String memberId) {
		// TODO Auto-generated method stub
		return dao.getCouponList(memberId);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void couponUsed(int couponNo) {
		// TODO Auto-generated method stub
		dao.couponUsed(couponNo);
	}
	
	
}

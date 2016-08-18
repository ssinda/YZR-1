package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

public interface CouponService {
	public List<CouponVO> getCouponList(String memberId);
	public void couponUsed(int couponNo);
}

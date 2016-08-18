package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.CouponVO;

public interface CouponDAO {
	
	public List<CouponVO> getCouponList(String memberId);
	public void couponUsed(int couponNo);
}

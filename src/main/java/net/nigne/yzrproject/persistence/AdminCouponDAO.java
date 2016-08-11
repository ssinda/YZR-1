package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.CouponVO;

public interface AdminCouponDAO {
	public List<String> getMemberList();
	public void persist(CouponVO vo);
	public boolean checkId(String member_id);
	public List<String> getClubMember(String club);
}

package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;

public interface LostDAO {

	public long getTotalCount(String member_id);
	public List<LostVO> getListPage(String member_id, Criteria criteria);
	public List<LostVO> getLostSearch(String member_id,String search,Criteria criteria);
	public long getSearchTotal(String member_id,String search);
	public MemberVO getMember(String member_id);
	public LostVO getLost(int no);

}

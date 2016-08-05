package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.RentVO;

public interface RentService {
	
	public long getTotalCount(String member_id);
	public List<RentVO> getListPage(String member_id,Criteria criteria);
	public List<RentVO> getRentSearch(String member_id,String search,Criteria criteria);
	public long getSearchTotal(String member_id,String search);
	public MemberVO getMember(String member_id);
	public RentVO getRent(int no);

}
package net.nigne.yzrproject.service;


import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PurchaseVO;

public interface PurchaseService {
	public long getTotalCount(String member_id);
	public List<PurchaseVO> getListPage(String member_id, Criteria criteria);
	public void delete_Store(int no);
	public void payPersist(PurchaseVO vo) throws Exception;
	public MemberVO getUser_Info(String member_id);
}

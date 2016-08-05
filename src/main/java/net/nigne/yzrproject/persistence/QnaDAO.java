package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;

public interface QnaDAO {

	public long getTotalCount(String member_id);
	public List<QnaVO> getListPage(String member_id, Criteria criteria);
	public List<QnaVO> getQnaSearch(String member_id,String search,Criteria criteria);
	public long getSearchTotal(String member_id,String search);
	public MemberVO getMember(String member_id);
	public QnaVO getQna(int no);
	
}

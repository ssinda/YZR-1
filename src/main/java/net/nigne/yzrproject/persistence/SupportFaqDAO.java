package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.FaqVO;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;

public interface SupportFaqDAO {
	public List<FaqVO> getFaqList(Criteria cri, String search, String category);
	public Long getFaqCount(String search, String category);
	public FaqVO getFaqDetail(int no);
	public void updateView_cnt(int no);
}

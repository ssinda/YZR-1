package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.FaqVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;

public interface SupportFaqService {
	public List<FaqVO> getFaqList(Criteria cri, String search, String category);
	public Long getFaqCount(String search, String category);
	public FaqVO getFaqDetail(int no);
}

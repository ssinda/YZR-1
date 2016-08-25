package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.FaqVO;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.persistence.SupportFaqDAO;
import net.nigne.yzrproject.persistence.SupportLostDAO;
import net.nigne.yzrproject.persistence.SupportQnaDAO;
import net.nigne.yzrproject.persistence.SupportRentDAO;

@Service
public class SupportFaqServiceImpl implements SupportFaqService {

	@Autowired
	private SupportFaqDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<FaqVO> getFaqList(Criteria cri, String search, String category) {
		// TODO Auto-generated method stub
		return dao.getFaqList(cri, search, category);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getFaqCount(String search, String category) {
		// TODO Auto-generated method stub
		return dao.getFaqCount(search, category);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public FaqVO getFaqDetail(int no) {
		// TODO Auto-generated method stub
		dao.updateView_cnt(no);
		return dao.getFaqDetail(no);
	}
}

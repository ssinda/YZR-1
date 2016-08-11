package net.nigne.yzrproject.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.GpaVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.ReviewVO;
import net.nigne.yzrproject.persistence.LoginDAO;
import net.nigne.yzrproject.persistence.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO dao;

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void reviewInsert(String member_id, String movie_id, String review_title, String review_content,
			String review_date, int review_cnt, String review_file, String review_like) throws Exception {
		dao.reviewInsert(member_id, movie_id, review_title, review_content, review_date, review_cnt, review_file,
				review_like);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public List<ReviewVO> getReview(String movie_id) {
		// TODO Auto-generated method stub
		return dao.getReview(movie_id);
		}
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public ReviewVO getReview_Read(int no) {
		// TODO Auto-generated method stub
		return dao.getReview_Read(no);
	}
	

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public long getTotalCount(String movie_id) {
		// TODO Auto-generated method stub
		return dao.getTotalCount(movie_id);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public List<ReviewVO> getListPage(String movie_id, Criteria criteria) {
		// TODO Auto-generated method stub
		return dao.getListPage(movie_id, criteria);
	}


	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void Reviewcnt_Update(int no, int review_cnt) throws Exception {
		// TODO Auto-generated method stub
		dao.Reviewcnt_Update(no, review_cnt);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void Review_Update(int no, String review_title, String review_content, String review_file) throws Exception {
		dao.Review_Update(no, review_title, review_content,review_file);
		
	}
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void delete_Review(int review_no) {
		// TODO Auto-generated method stub
		dao.delete_Review(review_no);
	}
	
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void delete_Review_Reply(int review_no) {
		// TODO Auto-generated method stub
		 dao.delete_Review_Reply(review_no);
	}
	

	
}
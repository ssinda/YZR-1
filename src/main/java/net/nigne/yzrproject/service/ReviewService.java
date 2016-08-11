package net.nigne.yzrproject.service;

import java.sql.Timestamp;
import java.util.List;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.GpaVO;
import net.nigne.yzrproject.domain.ReplyVO;
import net.nigne.yzrproject.domain.ReviewVO;

public interface ReviewService {
	

	public void reviewInsert(String member_id, String movie_id, String review_title, String review_content,
			String review_date, int review_cnt, String review_file, String review_like) throws Exception;
	public List<ReviewVO> getReview(String movie_id);
	public ReviewVO getReview_Read(int no);
	public long getTotalCount(String movie_id);
	public List<ReviewVO> getListPage(String movie_id, Criteria criteria);
	public void Reviewcnt_Update(int no, int review_cnt) throws Exception;
	public void delete_Review(int review_no);
	public void delete_Review_Reply(int review_no);
	public void Review_Update(int no,String review_title, String review_content, String review_file) throws Exception;

}
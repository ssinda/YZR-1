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
import net.nigne.yzrproject.domain.ReplyVO;
import net.nigne.yzrproject.domain.ReviewVO;
import net.nigne.yzrproject.persistence.LoginDAO;
import net.nigne.yzrproject.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO dao;

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void replyInsert(int reply_no,int review_no, String user_id, String reply_content, String reply_date,String reply_reply) throws Exception {
		dao.replyInsert(reply_no,review_no, user_id, reply_content, reply_date, reply_reply);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public List<ReplyVO> getReply(int review_no) {
		// TODO Auto-generated method stub
		return dao.getReply(review_no);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public List<ReplyVO> getReply_Reply(int review_no) {
		// TODO Auto-generated method stub
		return dao.getReply_Reply(review_no);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void reply_Reply_Insert(int reply_no, int review_no, String user_id, String reply_content, String reply_date,
			String reply_reply) throws Exception {
		// TODO Auto-generated method stub
		dao.reply_Reply_Insert(reply_no, review_no, user_id, reply_content, reply_date, reply_reply);
		
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public long getReplyCount(int review_no) {
		// TODO Auto-generated method stub
		return dao.getReplyCount(review_no);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public List<ReplyVO> getReplyPage(int review_no, Criteria criteria) {
		// TODO Auto-generated method stub
		return dao.getReplyPage(review_no, criteria);
	}



	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void delete_Reply(int no) {
		// TODO Auto-generated method stub
		dao.delete_Reply(no);
	}
	
	
}
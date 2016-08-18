package net.nigne.yzrproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.domain.ReplyVO;
import net.nigne.yzrproject.domain.ReviewVO;
import net.nigne.yzrproject.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService Reply_Service;

	@RequestMapping(value = "/movie/reply/{no}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reply_read(Locale locale, Model model, @PathVariable("no") int no,
			@PathVariable("page") Integer page) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			long replyTotal = Reply_Service.getReplyCount(no);
			List<ReplyVO> replypagelist = Reply_Service.getReplyPage(no, criteria);
			PageMaker pm = new PageMaker(criteria, replyTotal);
			Map<String, Object> map = new HashMap<>();
			map.put("l", replypagelist);
			map.put("p", pm);
			entity = new ResponseEntity<>(map, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/review/new/reply/{no}", method = RequestMethod.POST)
	public ResponseEntity<String> reply_insert(@RequestBody ReplyVO vo, @PathVariable("no") int no) {
		ResponseEntity<String> entity = null;
		try {
			Reply_Service.replyInsert(vo.getReply_no(), vo.getReview_no(), vo.getUser_id(), vo.getReply_content(),
					vo.getReply_date(), vo.getReply_reply());
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/review/new/reply_reply/{no}", method = RequestMethod.POST)
	public ResponseEntity<String> reply_reply_insert(@RequestBody ReplyVO vo, @PathVariable("no") int no) {
		ResponseEntity<String> entity = null;
		try {
			Reply_Service.reply_Reply_Insert(vo.getReply_no(), vo.getReview_no(), vo.getUser_id(),
					vo.getReply_content(), vo.getReply_date(), vo.getReply_reply());
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/movie/reply_delete/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReply(@PathVariable("no") Integer no) throws Exception {
		ResponseEntity<String> entity = null;
		try {
			Reply_Service.delete_Reply(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}

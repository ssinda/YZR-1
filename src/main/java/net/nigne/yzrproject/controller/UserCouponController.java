package net.nigne.yzrproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.service.UserCouponService;

@Controller
public class UserCouponController {
	
	@Autowired
	private UserCouponService service;
	
	@RequestMapping(value = "/user/coupon", method = RequestMethod.GET)
	public ModelAndView userCouponPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/coupon");
		return view;
	}
	@RequestMapping(value = "/user/coupon/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> couponList(@PathVariable("page") Integer page, HttpSession session) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			String member_id=(String)session.getAttribute("member_id");

			Map<String, Object> map = new HashMap<String, Object>();
			Criteria cri = new Criteria();
			cri.setPage(page);
			List<CouponVO> list=null;
				
			list = service.getCouponList(cri, member_id);
			
			Long couponTotal = service.getCouponTotal(member_id);
			PageMaker pm = new PageMaker(cri, couponTotal);
			
			map.put("coupon_list", list);
			map.put("paging", pm);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
}
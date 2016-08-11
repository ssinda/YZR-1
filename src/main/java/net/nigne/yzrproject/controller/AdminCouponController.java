package net.nigne.yzrproject.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
import net.nigne.yzrproject.service.AdminCouponService;

@Controller
public class AdminCouponController {
	
	@Autowired
	private AdminCouponService service;
	
	@RequestMapping(value = "/admin/usercoupon", method = RequestMethod.GET)
	public ModelAndView AdminUsercouponPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.addObject("member_list", service.getMemberList());
		view.setViewName("admin/usercoupon");
		return view;
	}
	@RequestMapping(value = "/admin/clubcoupon", method = RequestMethod.GET)
	public ModelAndView AdminClubcouponPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/clubcoupon");
		return view;
	}
	@RequestMapping(value = "/admin/usercoupon", method = RequestMethod.POST)
	public ModelAndView AdminUsercoupon(CouponVO vo, HttpSession session) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String coupon_date=sdf.format(cal.getTime().getTime());
		
		vo.setCoupon_date(coupon_date);
		vo.setCoupon_code("C" + vo.getMember_id());
		vo.setUsed("N");
		vo.setPublisher((String)session.getAttribute("member_id"));
		
		service.persist(vo);
		
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/user");
		return view;
	}
	@RequestMapping(value = "/admin/usercoupon/check", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkId(@RequestParam("member_id") String member_id) throws Exception{

		ResponseEntity<Boolean> entity = null;
		
		try{
			entity = new ResponseEntity<Boolean>(service.checkId(member_id), HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value = "/admin/clubcoupon", method = RequestMethod.POST)
	public ModelAndView AdminClubcoupon(@RequestParam("club") String club, CouponVO vo, HttpSession session) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String coupon_date=sdf.format(cal.getTime().getTime());
		
		List<String> list=service.getClubMember(club);
		for(String member_id : list){
			CouponVO cvo=new CouponVO();
			cvo.setCoupon_date(coupon_date);
			cvo.setUsed("N");
			cvo.setPublisher((String)session.getAttribute("member_id"));
			cvo.setCoupon_amount(vo.getCoupon_amount());
			cvo.setCoupon_code("C" + member_id);
			cvo.setMember_id(member_id);
			
			service.persistClubCoupon(cvo);
		}
		ModelAndView view=new ModelAndView();
		view.setViewName("admin/user");
		return view;
	}
}
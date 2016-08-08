package net.nigne.yzrproject.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.service.ReservationService;

@Controller
public class UserReservationController {
	
	@Autowired
	ReservationService reservation_service;
	
	@RequestMapping(value = "/user/reservation", method = RequestMethod.GET)
	public ModelAndView userReservationPage(HttpServletRequest request) throws Exception {
		
		ModelAndView view=new ModelAndView();
		
		view.addObject("today", new Date());
		view.setViewName("user/reservation");
		return view;
	}
	
	@RequestMapping(value = "/user/reservation/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> reservationListPage(@PathVariable("page") Integer page, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id");
		
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			
			//페이지처리를 위한 Criteria생성
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			criteria.setArticlePerPage(10);
			
			//검색한 영화 가져오기
			Map<String,Object> reservation = reservation_service.getReservationPage(member_id, criteria);
			
			//예매 갯수 가져오기
			long reservationTotal = reservation_service.getReservationTotal(member_id);
			
			//페이지 메이커에 총 글 갯수와 현재 페이지가 있는 Criteria를 넘겨서 페이징 값을 계산한다
			PageMaker pm = new PageMaker(criteria, reservationTotal);
			
			//최종적으로 목록과 페이징 처리값을 담아서
			Map<String,Object> map = new HashMap<>();
			map.put("l", reservation);
			map.put("p", pm);
			map.put("total", reservationTotal);
			
			//브라우저로 전송
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/user/reservation/{reservation_code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> reservationCancel(@PathVariable("reservation_code") String reservation_code, HttpServletRequest request) {
	
		ResponseEntity<String> entity = null;
		System.out.println(reservation_code);
		try{
			reservation_service.reservationCancel(reservation_code);
			//브라우저로 전송
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
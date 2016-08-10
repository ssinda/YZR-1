package net.nigne.yzrproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.service.MemberService;
import net.nigne.yzrproject.service.UserInfoService;

@Controller
public class UserInfoController {
	
	@Autowired
	private UserInfoService service;
	
	@Autowired // Class class = new 생성자(); 의 역할을 해주신 어노테이션 
	private MemberService memberService;
	
	@RequestMapping(value = "/user/member/edit", method = RequestMethod.GET)
	public ModelAndView memberEdit(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		MemberVO vo = memberService.select(member_id);
		ModelAndView view = new ModelAndView(); 
		view.addObject("vo",vo);
		view.setViewName("user/memberEdit"); // 위의 과정을 실행하고 인덱스 페이지로 넘어감
		return view;
		
	}
	
	@RequestMapping(value = "/user/member/edit", method = RequestMethod.POST)
	public ModelAndView memberEditPage(@ModelAttribute MemberVO vo, HttpServletRequest request) throws Exception {
		System.out.println(vo.getMember_pw());
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		service.userInfoUpdate(member_id, vo);
		ModelAndView view=new ModelAndView();
		view.setViewName("user/index");
		return view;
	}
	
	@RequestMapping(value = "/user/memberPwEdit", method = RequestMethod.GET)
	public ModelAndView memberPwEdit(HttpSession session) throws Exception {
		ModelAndView view=new ModelAndView();
		String member_id = (String)session.getAttribute("member_id");
		view.addObject("password", service.getMemberInfo(member_id).getMember_pw());
		view.setViewName("user/memberPwEdit");
		return view;
	}
	
	
	@RequestMapping(value = "/user/memberPwEdit", method = RequestMethod.POST)
	public ModelAndView newPwEdit(@RequestBody MemberVO vo) throws Exception {
		ModelAndView view=new ModelAndView();
		
		view.setViewName("user/memberPwEdit");
		return view;
	}
	
	
	@RequestMapping(value = "/user/pwFind", method = RequestMethod.POST)
	public ResponseEntity<Boolean> pwFind(@RequestBody MemberVO vo) throws Exception {
		ResponseEntity<Boolean> entity = null; // ↑ 이 부분이 잘 이해가 안감, 이건 무슨형태의 파라미터인가? 
//		MemberVO vo = new MemberVO();
//		vo.setMember_id(member_id);
//		vo.setQuestion(question);
//		vo.setAnswer(answer);
		System.out.println("m :" + vo.getMember_id());
		System.out.println("a :" + vo.getAnswer());
		System.out.println("q :" + vo.getQuestion());
		try{
			System.out.println(service.pwFind(vo));
			entity = new ResponseEntity<Boolean>(service.pwFind(vo),HttpStatus.OK); // 이 부분도 마찬가지!!
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
			System.out.println(e.getMessage()); 
		}
		return entity;
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public ModelAndView userDeletePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/delete");
		return view;
	}
	
	@RequestMapping(value = "/user/member/pwChange", method = RequestMethod.POST)
	public ModelAndView PwChange(@RequestParam("newPw") String newPw, HttpSession session) throws Exception {
		ModelAndView view=new ModelAndView();
		String member_id = (String)session.getAttribute("member_id");
		service.pwUpdate(member_id, newPw);
		view.setViewName("/index");
		return view;
	}
	
}
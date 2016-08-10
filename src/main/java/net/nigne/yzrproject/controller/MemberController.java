package net.nigne.yzrproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.service.MemberService;

@Controller // 컨트롤러 클래스에는 항상 이 어노테이션을 클래스 위에 작성해주어야 함
public class MemberController {
   
	@Autowired // Class class = new 생성자(); 의 역할을 해주신 어노테이션 
	private MemberService service;

	@RequestMapping(value = "/member", method = RequestMethod.POST) // url에 위의 주소가 입력되면 이 클래스? 이 메서드(만?) 호출? POST방식!!
	public ModelAndView member(MemberVO vo, HttpServletRequest request, @RequestParam("email1") String email1, @RequestParam("email2") String email2) throws Exception { // 모델앤뷰 타입은 무엇인가? 무슨 일을 하는가?
		vo.setGrade("normal"); // 추가로 객채에 칼럼값을 지정해줌
		vo.setEmail(email1 + "@" + email2);
		service.insert(vo); // 서비스 클래스의 객체의 메서드를 호출함 with 멤버VO 파라미터를 인자값으로 넘겨줌
		ModelAndView view = new ModelAndView();
		
		HttpSession session = request.getSession();
		session.setAttribute("member_id", vo.getMember_id());
		session.removeAttribute("error");
		view = new ModelAndView("redirect:/index");
		view.addObject("vo", vo);
		view.setViewName("user/memberConfirm"); // 위의 과정을 실행하고 인텍스 페이지로 넘어감
		return view;
	}
 
	@RequestMapping(value = "/member", method = RequestMethod.GET) // GET 방식으로 값을 전달받으면 이 메서드가 호출됨
	public String memberPage() throws Exception {
	return "member";
	}
	
	@RequestMapping(value = "/member/check", method = {RequestMethod.GET, RequestMethod.POST}) // 중복확인 체크 메서드 url , GET 방식
	public ResponseEntity<Boolean> memberCheck(@RequestParam("member_id") String member_id) throws Exception {
		ResponseEntity<Boolean> entity = null; // ↑ 이 부분이 잘 이해가 안감, 이건 무슨형태의 파라미터인가? 
		try{
			entity = new ResponseEntity<Boolean>(service.idCheck(member_id),HttpStatus.OK); // 이 부분도 마찬가지!!
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
	@RequestMapping(value = "user/member/delete", method =  RequestMethod.POST) // 중복확인 체크 메서드 url , GET 방식
	public ResponseEntity<Boolean> memberDelete(@RequestParam("member_pw") String member_pw, @RequestParam("member_id") String member_id) throws Exception {
		ResponseEntity<Boolean> entity = null; // ↑ 이 부분이 잘 이해가 안감, 이건 무슨형태의 파라미터인가? 
		try{
			entity = new ResponseEntity<Boolean>(service.pwCheck(member_pw, member_id),HttpStatus.OK); // 이 부분도 마찬가지!!
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
	@RequestMapping(value = "/user/member/deleteConfirm", method = RequestMethod.POST) // 중복확인 체크 메서드 url , GET 방식
	public ModelAndView memberDeleteConfirm(HttpServletRequest request, @RequestParam("pw") String pw) throws Exception {
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		System.out.println(pw);
		String member_id =  (String)session.getAttribute("member_id");
		try{
			service.delete(member_id);
			session.removeAttribute("member_id");
		}catch(Exception e){
			e.printStackTrace();
		}
		view.setViewName("index");
		return view;
	}
	
	@RequestMapping(value = "/user/idSearch", method = RequestMethod.GET)
	public ModelAndView idSearch(){
		ModelAndView view = new ModelAndView();
		view.setViewName("user/idSearch");
		return view;
	}
	
	@RequestMapping(value = "/user/pwSearch", method = RequestMethod.GET)
	public ModelAndView pwPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("user/pwSearch");
		return view;
	}
	
	@RequestMapping(value = "/user/pwSearch", method = RequestMethod.POST)
	public ModelAndView pwSearch(){
		ModelAndView view = new ModelAndView();
		view.setViewName("user/pwSearch");
		return view;
	}
	
}
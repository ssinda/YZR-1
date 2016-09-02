package net.nigne.yzrproject.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.service.MemberService;

@Controller 
public class MemberController {
   
	@Autowired 
	private MemberService service;
	
							 //ȸ������ POST���
	@RequestMapping(value = "/member", method = RequestMethod.POST) 
	public ModelAndView member(MemberVO vo, 
								@RequestParam("email1") String email1, 
								@RequestParam("email2") String email2,
								@RequestParam("year") String year,
								@RequestParam("month") String month,
								@RequestParam("day") String day) throws Exception {
		vo.setGrade("�����"); 
		vo.setEmail(email1 + "@" + email2);
		vo.setBirthday(year+month+day);
		service.insert(vo); 
		ModelAndView view = new ModelAndView();
		
		view.addObject("vo", vo);
		view.setViewName("user/memberConfirm"); 
		return view;
	}
	
							 // ȸ������ GET���
	@RequestMapping(value = "/member", method = RequestMethod.GET) 
	public String memberPage() throws Exception {
	return "member";
	}
	
							 // ���̵� �ߺ�üũ
	@RequestMapping(value = "/member/check", method = {RequestMethod.GET, RequestMethod.POST}) 
	public ResponseEntity<Boolean> memberCheck(@RequestParam("member_id") String member_id) throws Exception {
		ResponseEntity<Boolean> entity = null;  
		try{
			entity = new ResponseEntity<Boolean>(service.idCheck(member_id),HttpStatus.OK); 
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
								// ȸ��Ż��
	@RequestMapping(value = "user/member/delete", method =  RequestMethod.POST)
	public ResponseEntity<Boolean> memberDelete(@RequestBody MemberVO vo) throws Exception {
		ResponseEntity<Boolean> entity = null; 
		System.out.println("123");
		try{
			entity = new ResponseEntity<Boolean>(service.pwCheck(vo.getMember_pw(), vo.getMember_id()), HttpStatus.OK); 
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
							// ȸ��Ż�� Ȯ���� ������������ �̵�
	@RequestMapping(value = "/user/member/deleteConfirm", method = RequestMethod.POST) 
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
		view.setViewName("redirect:/index");
		return view;
	}
	
							 //���̵� ã��
	@RequestMapping(value = "/user/idSearch", method = RequestMethod.GET)
	public ModelAndView idSearchGet(){
		ModelAndView view = new ModelAndView();
		view.setViewName("user/idSearch");
		return view;
	}
	
	
							 // ��й�ȣ ã�� GET���
	@RequestMapping(value = "/user/pwSearch", method = RequestMethod.GET)
	public ModelAndView pwPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("user/pwSearch");
		return view;
	}
							// ��й�ȣ ã�� POST���
	@RequestMapping(value = "/user/pwSearch", method = RequestMethod.POST)
	public ModelAndView pwSearch(){
		ModelAndView view = new ModelAndView();
		view.setViewName("user/pwSearch");
		return view;
	}
	

		 // ȸ���������� GET���
	@RequestMapping(value = "/user/member/edit", method = RequestMethod.GET)
	public ModelAndView memberEdit(HttpServletRequest request) throws Exception {
	
	HttpSession session = request.getSession();
	String member_id = (String) session.getAttribute("member_id");
	MemberVO vo = service.select(member_id);
	ModelAndView view = new ModelAndView(); 
	view.addObject("vo",vo);
	view.setViewName("user/memberEdit"); 
	return view;
	
	}
	
		// ȸ���������� POST���
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
	
		 //��й�ȣ���� GET���
	@RequestMapping(value = "/user/memberPwEdit", method = RequestMethod.GET)
	public ModelAndView memberPwEdit(HttpSession session) throws Exception {
	ModelAndView view=new ModelAndView();
	String member_id = (String)session.getAttribute("member_id");
	view.addObject("password", service.getMemberInfo(member_id).getMember_pw());
	view.setViewName("user/memberPwEdit");
	return view;
	}
	
		 //��й�ȣ���� POST���
	@RequestMapping(value = "/user/memberPwEdit", method = RequestMethod.POST)
	public ModelAndView newPwEdit() throws Exception {
	ModelAndView view=new ModelAndView();
	
	view.setViewName("user/memberPwEdit");
	return view;
	}
	
			//���̵� ã��
	@RequestMapping(value = "/user/idFind", method = RequestMethod.POST)
	public ModelAndView idFind(@RequestParam("member_name") String member_name, @RequestParam("email") String email) throws Exception {
	
	ModelAndView view=new ModelAndView();
	view.addObject("idFind", service.idSearch(member_name, email));
	view.setViewName("user/idFind");
	return view;
	}
	
		//��й�ȣã��� ���ο� ��й�ȣ �����ϱ�
	@RequestMapping(value = "/user/pwFind", method = RequestMethod.POST)
	public ModelAndView pwFind(@RequestParam("member_id") String member_id, @RequestParam("question") String question, @RequestParam("answer") String answer) throws Exception {
	ModelAndView view=new ModelAndView();
	view.addObject("pwFindId", member_id);
	view.setViewName("user/pwFind");
	return view;
	}
	
	@RequestMapping(value = "/user/pwFindAndEdit", method = RequestMethod.POST)
	public ModelAndView pwFindAndEdit(@RequestParam("member_id") String member_id,@RequestParam("newPw") String newPw ) throws Exception {
	ModelAndView view=new ModelAndView();
	service.pwUpdate(member_id, newPw);
	view.setViewName("/index");
	return view;
	}
	
	
		 // �����������̵�
	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public ModelAndView userDeletePage() throws Exception {
	ModelAndView view=new ModelAndView();
	view.setViewName("user/delete");
	return view;
	}
	
			// ��й�ȣ ����
	@RequestMapping(value = "/user/member/pwChange", method = RequestMethod.POST)
	public ModelAndView PwChange(@RequestParam("newPw") String newPw, HttpSession session) throws Exception {
	ModelAndView view=new ModelAndView();
	String member_id = (String)session.getAttribute("member_id");
	service.pwUpdate(member_id, newPw);
	view.setViewName("redirect:/index");
	return view;
	}
	
	@RequestMapping(value = "/member/pwcheck", method =  RequestMethod.POST) 
	public ResponseEntity<Boolean> memberPwCheck(@RequestBody MemberVO vo) throws Exception {
		ResponseEntity<Boolean> entity = null;
		System.out.println(vo.getAnswer());
		try{
			entity = new ResponseEntity<Boolean>(service.pwFind(vo),HttpStatus.OK); 
		}catch(Exception e){
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
	@RequestMapping(value = "/member/point/{point}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> point(HttpSession session,
			@PathVariable("point") String point
			) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		String memberId = (String)session.getAttribute("member_id");
		try{
			int originPoint = service.getMember(memberId).getPoint();
			originPoint += Integer.parseInt(point);
			service.pointUpdate(memberId, originPoint);

			//�������� �����Ѵ�
			entity = new ResponseEntity<>(HttpStatus.OK);
			
		} catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
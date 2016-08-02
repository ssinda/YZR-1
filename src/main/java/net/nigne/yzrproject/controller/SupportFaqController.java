package net.nigne.yzrproject.controller;

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
import net.nigne.yzrproject.domain.FaqVO;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.service.SupportFaqService;
import net.nigne.yzrproject.service.SupportQnaService;
import net.nigne.yzrproject.service.SupportRentService;

@Controller
public class SupportFaqController {
	
	@Autowired
	private SupportFaqService service;
	
	@RequestMapping(value = "/support/faq", method = RequestMethod.GET)
	public ModelAndView supportFaqPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("support/faq");
		return view;
	}
	@RequestMapping(value = "/support/faq", method = RequestMethod.POST)
	public ModelAndView supportFaqSearch(@RequestParam("search") String search) throws Exception {
		ModelAndView view=new ModelAndView();
		view.addObject("search", search);
		view.setViewName("support/faq");
		return view;
	}
	@RequestMapping(value = "/support/faq/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> faqList(@PathVariable("page") Integer page, @RequestParam("search") String search,
			@RequestParam("category") String category, HttpSession session) {
		ResponseEntity<Map<String, Object>> entity = null;
		try{			
			Map<String, Object> map = new HashMap<String, Object>();
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			List<FaqVO> list=null;
				
			list = service.getFaqList(criteria, search, category);
			
			Long faqCount = service.getFaqCount(search, category);
			
			PageMaker pm = new PageMaker(criteria, faqCount);

			map.put("faq_list", list);
			map.put("count", faqCount);
			map.put("paging", pm);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	@RequestMapping(value = "/support/faq/read/{no}", method = RequestMethod.GET)
	public ModelAndView supportNoticeReadPage(@PathVariable("no") Integer no) throws Exception {
		
		ModelAndView view=new ModelAndView();
		view.addObject("vo", service.getFaqDetail(no));
		view.setViewName("support/faqRead");
		return view;
	}
}
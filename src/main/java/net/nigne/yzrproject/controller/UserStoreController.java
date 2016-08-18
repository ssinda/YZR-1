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
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.PageMaker;
import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.service.PurchaseService;

@Controller
public class UserStoreController {
	
	@Autowired
	private PurchaseService purchase_Service;
	
	
	@RequestMapping(value = "/user/store", method = RequestMethod.GET)
	public ModelAndView userStorePage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/store");
		return view;
	}
	@RequestMapping(value = "/user/store/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("page") Integer page, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String member_id=(String)session.getAttribute("member_id");
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Criteria criteria = new Criteria();
			criteria.setArticlePerPage(5);
			criteria.setPage(page);
			
			long storeTotal = purchase_Service.getTotalCount(member_id);
			List<PurchaseVO> list = purchase_Service.getListPage(member_id, criteria);
			PageMaker pm = new PageMaker(criteria, storeTotal);
			Map<String, Object> map = new HashMap<>();
			map.put("l", list);
			map.put("p", pm);
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		return entity;
	}
	
	@RequestMapping(value = "/user/store/cancel/{no}", method = RequestMethod.DELETE) 
	public ResponseEntity<String> deleteproduct(@PathVariable("no") Integer no) throws Exception{ 
		ResponseEntity<String> entity = null; 
		try{ 
			purchase_Service.delete_Store(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK); 
		}catch(Exception e){ 
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); 
		} 
		return entity; 
	} 
}
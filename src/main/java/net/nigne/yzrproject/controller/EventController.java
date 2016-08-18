package net.nigne.yzrproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.EmpVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.service.EmpService;
import net.nigne.yzrproject.service.NoticeService;
import net.nigne.yzrproject.service.PurchaseService;

@Controller
public class EventController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String store(Locale locale, Model model) throws Exception {
		List<NoticeVO> event = service.getEvent();
		model.addAttribute("event_list", event);
		return "event";
	}
}
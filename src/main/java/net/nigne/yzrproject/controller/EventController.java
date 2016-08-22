package net.nigne.yzrproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.service.NoticeService;

@Controller
public class EventController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String store(HttpSession session, Model model) throws Exception {
		session.setAttribute("menu", "EVENT&CULTURE");

		List<NoticeVO> event = service.getEvent();
		model.addAttribute("event_list", event);

		return "event";
	}
}
package net.nigne.yzrproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
	
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public ModelAndView moviePage(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		session.setAttribute("menu", "MOVIE");
		ModelAndView view=new ModelAndView();
		view.setViewName("movie");
		return view;
	}
}
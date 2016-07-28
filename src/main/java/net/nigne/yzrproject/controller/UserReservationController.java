package net.nigne.yzrproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserReservationController {
	
	@RequestMapping(value = "/user/reservation", method = RequestMethod.GET)
	public ModelAndView userReservationPage() throws Exception {
		ModelAndView view=new ModelAndView();
		view.setViewName("user/reservation");
		return view;
	}
}
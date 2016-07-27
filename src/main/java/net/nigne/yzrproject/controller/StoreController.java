package net.nigne.yzrproject.controller;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.nigne.yzrproject.domain.EmpVO;
import net.nigne.yzrproject.service.EmpService;

@Controller
public class StoreController {
	@Autowired
	private EmpService service;

	@RequestMapping(value = "/culture_shop", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		
		return "culture_shop";
	}
	
}
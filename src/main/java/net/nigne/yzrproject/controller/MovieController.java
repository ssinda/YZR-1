package net.nigne.yzrproject.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movie_service;
	
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public ModelAndView moviePage(HttpServletRequest request, Model model) throws Exception {
		HttpSession session=request.getSession();
		session.setAttribute("menu", "MOVIE");
		ModelAndView view=new ModelAndView();
		view.setViewName("movie");
		List<MovieVO> movie_chart = movie_service.getMovieChart("n", "평점순");
		model.addAttribute("movie_chart", movie_chart);
		
		return view;
	}
	
	@RequestMapping(value = "/movie/main", method = RequestMethod.GET)
	public ResponseEntity<List<MovieVO>> movie_chart(@RequestParam("category") String category, HttpServletRequest request){
		ResponseEntity<List<MovieVO>> entity = null;
		List<MovieVO> movie_chart = null;
		try{
			if("chart".equals(category)){
				movie_chart = movie_service.getMovieChart("off", "예매율순");
			}else if("schedule".equals(category)){
				movie_chart = movie_service.getMovieSchedule();
			}
			entity = new ResponseEntity<List<MovieVO>>(movie_chart, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<MovieVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/movie/main/select", method = RequestMethod.GET)
	public ResponseEntity<List<MovieVO>> play(@RequestParam("play") String play, @RequestParam("order") String order, HttpServletRequest request){
		ResponseEntity<List<MovieVO>> entity = null;
		List<MovieVO> movie_chart = null;
		
		try{
			movie_chart = movie_service.getMovieChart(play, order);
			entity = new ResponseEntity<List<MovieVO>>(movie_chart, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<MovieVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
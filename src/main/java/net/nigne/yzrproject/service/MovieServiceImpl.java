package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.persistence.MovieDAO;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public MovieVO getMovie(String movie_id) {
		return dao.getMovie(movie_id);
	}

	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> genreMovie(String member_id) {
		return dao.genreMovie(member_id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> actorMovie(String member_id) {
		return dao.actorMovie(member_id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> directorMovie(String member_id) {
		return dao.directorMovie(member_id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> basicMovie() {
		return dao.basicMovie();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getMovieChart(String play, String order) {
		return dao.getMovieChart(play, order);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getMovieSchedule() {
		return dao.getMovieSchedule();
	}

}

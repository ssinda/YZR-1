package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;

public interface MovieService {
	public MovieVO getMovie(String movie_id);
	public List<MovieVO> genreMovie(String member_id);
	public List<MovieVO> actorMovie(String member_id);
	public List<MovieVO> directorMovie(String member_id);
	public List<MovieVO> basicMovie();
	public List<MovieVO> getMovieChart(String play, String order);
	public List<MovieVO> getMovieSchedule();
}

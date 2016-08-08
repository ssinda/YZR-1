package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;

public interface MovieDAO {
	public MovieVO getMovie(String movie_id);
	
	public List<MovieVO> genreMovie(String member_id);		// 추천장르영화목록
	public List<MovieVO> actorMovie(String member_id);		// 추천배우영화목록
	public List<MovieVO> directorMovie(String member_id);	// 추천감독영화목록
	public List<MovieVO> basicMovie();						// 기본영화추천목록
	
	public List<MovieVO> getMovieChart(String play, String order);	// 무비차트
	public List<MovieVO> getMovieSchedule();	// 상영예정작
}

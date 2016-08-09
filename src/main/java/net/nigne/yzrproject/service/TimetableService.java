package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TimetableVO;

public interface TimetableService {
	public Map<String,Object> getTimetable(String theater_id, String day);
	public List<MovieVO> getMovieInfo(String theater_id, String day);
}

package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.TimetableVO;
import net.nigne.yzrproject.persistence.TimetableDAO;

@Service
public class TimetableServiceImpl implements TimetableService {
	
	@Autowired
	private TimetableDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public Map<String,Object> getTimetable(String theater_id, String day) {
		return dao.getTimetable(theater_id, day);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getMovieInfo(String theater_id, String day) {
		return dao.getMovieInfo(theater_id, day);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<GenreVO> getMovieGenre(String theater_id, String day) {
		return dao.getMovieGenre(theater_id, day);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<PlexVO> getPlexInfo(String theater_id, String day) {
		return dao.getPlexInfo(theater_id, day);
	}

}

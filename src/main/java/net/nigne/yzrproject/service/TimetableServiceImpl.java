package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.TimetableVO;
import net.nigne.yzrproject.persistence.TimetableDAO;

@Service
public class TimetableServiceImpl implements TimetableService {
	
	@Autowired
	private TimetableDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<TimetableVO> getTimetable(String theater_id, String day) {
		return dao.getTimetable(theater_id, day);
	}
	
	@Override
	public List<TimetableVO> getList(String movie, String theater, String date, String plex_number) {
		// TODO Auto-generated method stub
		return dao.getList(movie, theater, date, plex_number);
	}

	@Override
	public List<String> getPlexNum(String movie, String theater, String date) {
		// TODO Auto-generated method stub
		return dao.getPlexNum(movie, theater, date);
	}

}

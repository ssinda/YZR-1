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

}

package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.TimetableVO;

public interface TimetableService {
	public List<TimetableVO> getTimetable(String theater_id, String day);
}

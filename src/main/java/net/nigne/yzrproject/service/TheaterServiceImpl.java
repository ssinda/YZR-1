package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.persistence.TheaterDAO;

@Service
public class TheaterServiceImpl implements TheaterService {
	
	@Autowired
	private TheaterDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<TheaterVO> getTheater(String location) {
		return dao.getTheater(location);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<TheaterVO> getList(String theater) {
		// TODO Auto-generated method stub
		return dao.getList(theater);
	}

	@Override
	public List<Long> getLocalTheaterNum() {
		// TODO Auto-generated method stub
		return dao.getLocalTheaterNum();
	}

	@Override
	public List<TheaterVO> getLocal() {
		// TODO Auto-generated method stub
		return dao.getLocal();
	}

	@Override
	public List<TheaterVO> getTheaterId(String theaterName) {
		// TODO Auto-generated method stub
		return dao.getTheaterId(theaterName);
	}

	@Override
	public List<TheaterVO> getTheaterList(String theaterId) {
		// TODO Auto-generated method stub
		return dao.getTheaterList(theaterId);
	}

}

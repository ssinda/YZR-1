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

}

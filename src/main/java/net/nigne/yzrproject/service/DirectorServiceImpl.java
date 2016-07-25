package net.nigne.yzrproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.persistence.DirectorDAO;

@Service
public class DirectorServiceImpl implements DirectorService {
	
	@Autowired
	private DirectorDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public String getMovie_director(String movie_id) {
		return dao.getMovie_director(movie_id);
	}

}

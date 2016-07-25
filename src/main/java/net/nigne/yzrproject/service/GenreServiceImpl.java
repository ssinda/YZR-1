package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.persistence.GenreDAO;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public String getMovie_genre(String movie_id) {
		return dao.getMovie_genre(movie_id);
	}

}

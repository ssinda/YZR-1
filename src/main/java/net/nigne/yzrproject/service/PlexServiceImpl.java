package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.persistence.PlexDAO;

@Service
public class PlexServiceImpl implements PlexService {
	
	@Autowired
	private PlexDAO dao;

	@Override
	public List<PlexVO> getList(String plexNum, String theaterId) {
		// TODO Auto-generated method stub
		return dao.getList(plexNum, theaterId);
	}

}

package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.PlexVO;

public interface PlexService {
	
	public List<PlexVO> getList(String plexNum, String theaterId);

}

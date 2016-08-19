package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.PlexVO;

public interface PlexDAO {
	
	public List<PlexVO> getList(String plexNum, String theaterId);

}

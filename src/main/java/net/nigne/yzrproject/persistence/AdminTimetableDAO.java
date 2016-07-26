package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

public interface AdminTimetableDAO {
	public List<TheaterVO> getTheatername();
	public List<String> getPlexnumber();
	public void persist(TimetableVO vo);

}

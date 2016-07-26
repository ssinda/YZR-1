package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

@Repository
public class AdminTimetableDAOImpl implements AdminTimetableDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TheaterVO> getTheatername() {
		// TODO Auto-generated method stub
		List<TheaterVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> cq=cb.createQuery(TheaterVO.class);
		Root<TheaterVO> root = cq.from(TheaterVO.class);
		cq.select(root);
		try{
			TypedQuery<TheaterVO> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<String> getPlexnumber() {
		// TODO Auto-generated method stub
		List<String> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<PlexVO> root = cq.from(PlexVO.class);
		cq.select(root.get("plex_number"));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public void persist(TimetableVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
}

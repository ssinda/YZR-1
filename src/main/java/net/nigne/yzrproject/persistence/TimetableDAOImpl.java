package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.TimetableVO;

@Repository
public class TimetableDAOImpl implements TimetableDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TimetableVO> getTimetable(String theater_id, String day) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimetableVO> cq = cb.createQuery(TimetableVO.class);
		Root<TimetableVO> root = cq.from(TimetableVO.class);
		
		cq.where(cb.and(cb.equal(root.get("theater_id"), theater_id), cb.like(root.get("start_time"), "%"+day+"%")));
		
		TypedQuery<TimetableVO> tq = entityManager.createQuery(cq);
		List<TimetableVO> tt = tq.getResultList();
		return tt;
	}
	
	@Override
	public List<TimetableVO> getList(String movie, String theater, String date, String plex_number) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimetableVO> mainQuery = cb.createQuery(TimetableVO.class);
		Root<TimetableVO> mainQueryroot = mainQuery.from(TimetableVO.class);

		System.out.println("date = " + date);
		// select * from theater where theater_area = '지역이름'
		mainQuery.select(mainQueryroot);
		mainQuery.where(cb.equal(mainQueryroot.get("movie_id"), movie), cb.equal(mainQueryroot.get("theater_id"), theater), cb.equal(cb.substring(mainQueryroot.get("start_time"), 1, 10), date),cb.equal(mainQueryroot.get("plex_number"), plex_number));
				
		TypedQuery<TimetableVO> tq = entityManager.createQuery(mainQuery);
		List<TimetableVO> list = tq.getResultList();
		System.out.println(list.size());
		
		return list;
	}

	@Override
	public List<String> getPlexNum(String movie, String theater, String date) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> mainQuery = cb.createQuery(String.class);
		Root<TimetableVO> mainQueryroot = mainQuery.from(TimetableVO.class);
		
		mainQuery.select(mainQueryroot.get("plex_number")).distinct(true);
		mainQuery.where(cb.equal(mainQueryroot.get("movie_id"), movie), cb.equal(mainQueryroot.get("theater_id"), theater), cb.equal(cb.substring(mainQueryroot.get("start_time"), 1, 10), date));
		
		TypedQuery<String> tq = entityManager.createQuery(mainQuery);
		List<String> list = tq.getResultList();
				
		return list;
	}

}

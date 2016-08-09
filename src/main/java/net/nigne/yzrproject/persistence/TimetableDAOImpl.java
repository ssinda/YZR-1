package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TimetableVO;

@Repository
public class TimetableDAOImpl implements TimetableDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Map<String, Object> getTimetable(String theater_id, String day) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<TimetableVO> root = cq.from(TimetableVO.class);
		
		cq.select(root.get("movie_id"));
		cq.distinct(true);
		cq.where(cb.and(cb.equal(root.get("theater_id"), theater_id), cb.like(root.get("start_time"), "%"+day+"%")));
		
		TypedQuery<String> tq = entityManager.createQuery(cq);
		List<String> tt_movie_id = tq.getResultList();
		
		Predicate p = cb.equal(root.get("theater_id"), theater_id);
		Predicate p2 = cb.like(root.get("start_time"), "%"+day+"%");
		
		CriteriaQuery<TimetableVO> ttcq = cb.createQuery(TimetableVO.class);
		Root<TimetableVO> ttroot = ttcq.from(TimetableVO.class);
		TypedQuery<TimetableVO> ttq = null;
		Map<String, Object> ttmap = new HashMap<>();
		List<TimetableVO> tt = null;
		
		String m_name = "";
		for(int i=0; i<tt_movie_id.size(); i++){
			ttcq.where(cb.and(p, p2, cb.equal(ttroot.get("movie_id"), tt_movie_id.get(i))));
			ttq = entityManager.createQuery(ttcq);
			tt = ttq.getResultList();
			m_name = Integer.toString(i);
			ttmap.put(m_name, tt);
		}
		
		return ttmap;
	}

	@Override
	public List<MovieVO> getMovieInfo(String theater_id, String day) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<TimetableVO> root = cq.from(TimetableVO.class);
		
		cq.select(root.get("movie_id"));
		cq.distinct(true);
		cq.where(cb.and(cb.equal(root.get("theater_id"), theater_id), cb.like(root.get("start_time"), "%"+day+"%")));
		
		TypedQuery<String> tq = entityManager.createQuery(cq);
		List<String> tt_movie_id = tq.getResultList();
		
		CriteriaQuery<MovieVO> mcq = cb.createQuery(MovieVO.class);
		Root<MovieVO> mroot = mcq.from(MovieVO.class);
		TypedQuery<MovieVO> mtq = null;
		List<MovieVO> mlist = new ArrayList<MovieVO>();
		
		for(int i=0; i<tt_movie_id.size(); i++){
			mcq.where(cb.equal(mroot.get("movie_id"), tt_movie_id.get(i)));
			mtq = entityManager.createQuery(mcq);
			mlist.add(mtq.getSingleResult());
		}
		
		return mlist;
	}

}

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

}

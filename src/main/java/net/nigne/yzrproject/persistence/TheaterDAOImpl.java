package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.TheaterVO;

@Repository
public class TheaterDAOImpl implements TheaterDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TheaterVO> getTheater(String location) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> cq = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> root = cq.from(TheaterVO.class);
		
		cq.where(cb.equal(root.get("theater_area"), location));
		cq.orderBy(cb.asc(root.get("theater_name")));
		TypedQuery<TheaterVO> tq = entityManager.createQuery(cq);
		List<TheaterVO> theater_list = tq.getResultList();
		return theater_list;
	}

}

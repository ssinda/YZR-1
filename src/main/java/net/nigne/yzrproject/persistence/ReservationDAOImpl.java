package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.ReservationVO;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ReservationVO> getReservation_list(String member_id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReservationVO> cq = cb.createQuery(ReservationVO.class);
		Root<ReservationVO> root = cq.from(ReservationVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.where(p).orderBy(cb.desc(root.get("reservation_date")));
		
		TypedQuery<ReservationVO> tq = entityManager.createQuery(cq);
		List<ReservationVO> list = tq.getResultList();
		return list;
	}

}

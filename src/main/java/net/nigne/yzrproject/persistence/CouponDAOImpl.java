package net.nigne.yzrproject.persistence;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.CouponVO;

@Repository
public class CouponDAOImpl implements CouponDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CouponVO> getCouponList(String memberId) {
		// TODO Auto-generated method stub
		System.out.println("DAO ID = " + memberId);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CouponVO> mainQuery = cb.createQuery(CouponVO.class);
		Root<CouponVO> mainQueryroot = mainQuery.from(CouponVO.class);
		
		mainQuery.select(mainQueryroot).orderBy(cb.desc(mainQueryroot.get("no")), cb.desc(mainQueryroot.get("used")));
		mainQuery.where(cb.equal(mainQueryroot.get("member_id"), memberId));

		TypedQuery<CouponVO> tq = entityManager.createQuery(mainQuery);
		List<CouponVO> list = tq.getResultList();
		
		return list;
		
	}
	
	
}

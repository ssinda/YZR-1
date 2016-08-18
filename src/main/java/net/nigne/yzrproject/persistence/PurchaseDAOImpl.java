package net.nigne.yzrproject.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PurchaseVO;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void payPersist(PurchaseVO vo) throws Exception{
		entityManager.persist(vo);
	}

	@Override
	public MemberVO getUser_Info(String member_id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq = cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		
		cq.where(cb.equal(root.get("member_id"), member_id));
		
		TypedQuery<MemberVO> tq = entityManager.createQuery(cq);
		MemberVO vo = tq.getSingleResult();
		
		return vo;
	}

}

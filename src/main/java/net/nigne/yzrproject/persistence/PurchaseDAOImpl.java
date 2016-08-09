package net.nigne.yzrproject.persistence;

<<<<<<< HEAD
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.domain.ReplyVO;
=======
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.PurchaseVO;
>>>>>>> master

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
<<<<<<< HEAD
	public long getTotalCount(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(long.class);
		Root<PurchaseVO> root = cq.from(PurchaseVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(cb.count(root.get("member_id")));
		cq.where(p);
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long totalcount = tq.getSingleResult();
		return totalcount;
	}


	@Override
	public List<PurchaseVO> getListPage(String member_id, Criteria criteria) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PurchaseVO> cq = cb.createQuery(PurchaseVO.class);
		Root<PurchaseVO> root = cq.from(PurchaseVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(root);
		cq.where(p).orderBy(cb.desc(root.get("no")));
		TypedQuery<PurchaseVO> tq = entityManager.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());
		List<PurchaseVO> rentpagelist = tq.getResultList();
		return rentpagelist;
	}
	@Override
	public void delete_Store(int no) {
		PurchaseVO vo = entityManager.find(PurchaseVO.class, no);
		entityManager.remove(vo);
	}


=======
	public void payPersist(PurchaseVO vo) throws Exception{
		entityManager.persist(vo);
	}
>>>>>>> master

}

package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<NoticeVO> getNotice() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeVO> cq = cb.createQuery(NoticeVO.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		cq.orderBy(cb.desc(root.get("notice_date")));
		
		TypedQuery<NoticeVO> tq = entityManager.createQuery(cq).setFirstResult(0).setMaxResults(5);
		List<NoticeVO> list = tq.getResultList();
		return list;
	}

	@Override
	public List<NoticeVO> getEvent() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeVO> cq = cb.createQuery(NoticeVO.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		
		cq.where(cb.like(root.get("notice_category"), "%¿Ã∫•∆Æ%"));
		cq.orderBy(cb.desc(root.get("notice_date")));
		
		TypedQuery<NoticeVO> tq = entityManager.createQuery(cq);
		List<NoticeVO> list = tq.getResultList();
		
		return list;
	}

}

package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
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
import net.nigne.yzrproject.domain.FaqVO;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;

@Repository
public class SupportFaqDAOImpl implements SupportFaqDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<FaqVO> getFaqList(Criteria cri, String search, String category) {
		// TODO Auto-generated method stub
		List<FaqVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<FaqVO> cq=cb.createQuery(FaqVO.class);
		Root<FaqVO> root = cq.from(FaqVO.class);
		cq.select(root);
		cq.orderBy(cb.desc(root.get("no")));
		if(!search.equals("") && !category.equals("all")){
			cq.where(cb.like(root.get("title"),"%" + search + "%"), cb.equal(root.get("category"), category));
		}else{
			if(!search.equals("")){
				cq.where(cb.like(root.get("title"),"%" + search + "%"));
			}
			if(!category.equals("all")){
				cq.where(cb.equal(root.get("category"), category));
			}		
		}
		try{
			TypedQuery<FaqVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public Long getFaqCount(String search, String category) {
		// TODO Auto-generated method stub
		Long faqCount = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<FaqVO> root = cq.from(FaqVO.class);
		cq.select(cb.count(root));
		if(!search.equals("") && !category.equals("all")){
			cq.where(cb.like(root.get("title"),"%" + search + "%"), cb.equal(root.get("category"), category));
		}else{
			if(!search.equals("")){
				cq.where(cb.like(root.get("title"),"%" + search + "%"));
			}
			if(!category.equals("all")){
				cq.where(cb.equal(root.get("category"), category));
			}		
		}
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			faqCount=tq.getSingleResult();
			return faqCount;
		}catch(Exception e){
			return faqCount;
		}
	}
	@Override
	public FaqVO getFaqDetail(int no) {
		// TODO Auto-generated method stub
		FaqVO vo = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<FaqVO> cq=cb.createQuery(FaqVO.class);
		Root<FaqVO> root = cq.from(FaqVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("no"), no));
		try{
			TypedQuery<FaqVO> tq = entityManager.createQuery(cq);
			vo=tq.getSingleResult();
			return vo;
		}catch(Exception e){
			return vo;
		}
	}
}

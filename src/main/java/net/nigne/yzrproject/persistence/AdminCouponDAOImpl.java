package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

@Repository
public class AdminCouponDAOImpl implements AdminCouponDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<String> getMemberList() {
		// TODO Auto-generated method stub
		List<String> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root.get("member_id"));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public void persist(CouponVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public boolean checkId(String member_id) {
		// TODO Auto-generated method stub
		boolean check=false;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root.get("member_id"));
		cq.where(cb.equal(root.get("member_id"), member_id));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			if(!tq.getSingleResult().isEmpty()){
				check=true;
			}
			return check;
		}catch(Exception e){
			return check;
		}
	}
	@Override
	public List<String> getClubMember(String club) {
		// TODO Auto-generated method stub
		List<String> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root.get("member_id"));
		cq.where(cb.equal(root.get("club"), club));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
		}catch(Exception e){
			return list;
		}
	}
}

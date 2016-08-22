package net.nigne.yzrproject.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
			
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> mainQuery = cb.createQuery(MemberVO.class);
		Root<MemberVO> mainQueryroot = mainQuery.from(MemberVO.class);
		
		mainQuery.select(mainQueryroot);
		mainQuery.where(cb.equal(mainQueryroot.get("member_id"), member_id));
		
		TypedQuery<MemberVO> tq = entityManager.createQuery(mainQuery);
		List<MemberVO> list = tq.getResultList();
		
		return list.get(0);
	}
	
	
	

}

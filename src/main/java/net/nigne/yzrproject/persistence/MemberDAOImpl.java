package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.NoticeVO;

@Repository // 이것은 왜?
public class MemberDAOImpl implements MemberDAO {

    @PersistenceContext // 이건 또 뭐야?
	private EntityManager entityManager;
	
	@Override // 왜 메서드는 다 오버라이드 어노테이션이 적용된게 기본값인 것인가?
	public void insert(MemberVO vo) {
		entityManager.persist(vo);

	}

	@Override
	public MemberVO select(String member_id) {
		
		MemberVO vo = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq = cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("member_id"), member_id));
		try{
			TypedQuery<MemberVO> tq = entityManager.createQuery(cq);
			vo =tq.getSingleResult();
			return vo;
		}catch(Exception e){
			return vo;
		}
		//select * from member where member_id = aaaa;
		
	}

	@Override
	public void update(String member_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String member_id) {
		MemberVO vo = entityManager.find(MemberVO.class, member_id);
		entityManager.remove(vo);
	}

	@Override
	public boolean idCheck(String member_id) {
		String check=null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root.get("member_id"));
		cq.where(cb.equal(root.get("member_id"), member_id));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			check = tq.getSingleResult();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean pwCheck(String member_pw, String member_id) {
		String check=null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root.get("member_pw"));
		cq.where(cb.equal(root.get("member_id"), member_id));
		TypedQuery<String> tq = entityManager.createQuery(cq);
		check = tq.getSingleResult();
		if(check == member_pw || member_pw.equals(check)){
			
			return true;
		}else{
			return false;
		}
	}

}

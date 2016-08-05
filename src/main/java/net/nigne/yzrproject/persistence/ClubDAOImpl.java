package net.nigne.yzrproject.persistence;

import java.sql.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.jpa.criteria.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.GpaVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.ReservationVO;
import net.nigne.yzrproject.domain.ReviewVO;

@Repository
public class ClubDAOImpl implements ClubDAO {
			
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public MemberVO getMember(String member_id) {
		MemberVO vo = new MemberVO();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq = cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(root);
		cq.where(p);
		TypedQuery<MemberVO> tq = entityManager.createQuery(cq);
		vo = tq.getSingleResult();
		
		return vo;
	}
	@Override
	public void Member_club_Update(String member_id, String club) throws Exception {
		// TODO Auto-generated method stub
		MemberVO vo = entityManager.find(MemberVO.class,getMember(member_id).getMember_id());
		MemberVO mergevo = entityManager.merge(vo);
		mergevo.setClub(club);
		System.out.println(member_id+"叔楳けけけけけけけけけけけけけけけけけけけけけけけけけけけけけけ");
	}
}

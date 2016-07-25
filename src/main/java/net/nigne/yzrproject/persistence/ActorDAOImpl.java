package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.ReservationVO;

@Repository
public class ActorDAOImpl implements ActorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getMovie_actor(String member_id) {
		String actor = "";
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> actorQuery = cb.createQuery(String.class);
		Root<ActorVO> actorRoot = actorQuery.from(ActorVO.class);
		
		Subquery<ReservationVO> subQuery = actorQuery.subquery(ReservationVO.class);
		Root<ReservationVO> subQueryRoot = subQuery.from(ReservationVO.class);
		subQuery.select(subQueryRoot.get("movie_id"));
		subQuery.where(cb.equal(subQueryRoot.get("member_id"), member_id));
		
		actorQuery.select(actorRoot.get("actor_name"));
		actorQuery.where(actorRoot.get("movie_id").in(subQuery));
		actorQuery.groupBy(actorRoot.get("actor_name"));
		actorQuery.orderBy(cb.desc(cb.count(actorRoot.get("actor_name"))));
		
		TypedQuery<String> tq = entityManager.createQuery(actorQuery).setFirstResult(0).setMaxResults(1);
		
		actor = tq.getSingleResult();
		
		return actor;
	}

}

package net.nigne.yzrproject.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.DirectorVO;

@Repository
public class DirectorDAOImpl implements DirectorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getMovie_director(String movie_id) {
		String director = "";
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<DirectorVO> root = cq.from(DirectorVO.class);
		cq.select(root.get("director_name"));
		cq.where(cb.equal(root.get("movie_id"), movie_id));
		
		TypedQuery<String> tq = entityManager.createQuery(cq);
		director = tq.getSingleResult();
		return director;
	}

}

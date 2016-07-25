package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.ReservationVO;

@Repository
public class GenreDAOImpl implements GenreDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getMovie_genre(String movie_id) {
		String genre = "";
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<GenreVO> root = cq.from(GenreVO.class);
		cq.select(root.get("movie_genre"));
		cq.where(cb.equal(root.get("movie_id"), movie_id));
		
		TypedQuery<String> tq = entityManager.createQuery(cq);
		genre = tq.getSingleResult();
		return genre;
	}

}

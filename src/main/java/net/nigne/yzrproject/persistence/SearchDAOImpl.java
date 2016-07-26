/** 
*
*/
package net.nigne.yzrproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.SearchVO;

/** 
* @FileName : SearchDAOImpl.java 
* @Package  : net.nigne.yzrproject.persistence 
* @Date     : 2016. 7. 21. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Repository
public class SearchDAOImpl implements SearchDAO {

	@PersistenceContext
	EntityManager em;
	
	
	/*------------Movie Search------------*/
	
	/** 
	* @Method Name	: getSearch 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public List<MovieVO> getSearch(String search) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MovieVO> cq = cb.createQuery(MovieVO.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		Predicate p = cb.like(root.get("title"), "%"+search+"%");
		
		Expression<String> decode = cb.function("decode", String.class, root.get("status"),
				    cb.literal("play"),cb.literal(1),
				    cb.literal("schedule"),cb.literal(2),
				    cb.literal("end"),cb.literal(3));
		
		cq.select(root).where(p).orderBy(cb.asc(decode));
		TypedQuery<MovieVO> tq = em.createQuery(cq);
		List<MovieVO> list = tq.getResultList();

		return list;
	}

	/** 
	* @Method Name	: getListPage 
	* @Method 설명	: 
	* @param search
	* @param criteria
	* @return 
	*/
	@Override
	public List<MovieVO> getListPage(String search, Criteria criteria) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MovieVO> cq = cb.createQuery(MovieVO.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		Predicate p = cb.like(root.get("title"), "%"+search+"%");
		
		Expression<String> decode = cb.function("decode", String.class, root.get("status"),
			    cb.literal("play"),cb.literal(1),
			    cb.literal("schedule"),cb.literal(2),
			    cb.literal("end"),cb.literal(3));
		
		cq.select(root).where(p).orderBy(cb.asc(decode));
		TypedQuery<MovieVO> tq = em.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());
		List<MovieVO> pageList = tq.getResultList();

		return pageList;
		
	}

	/** 
	* @Method Name	: getTotalCount 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public long getTotalCount(String search) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		Predicate p = cb.like(root.get("title"), "%"+search+"%");
		cq.select(cb.countDistinct(root.get("title")));
		cq.where(p);
		TypedQuery<Long> tq = em.createQuery(cq);
		long  totalCount = tq.getSingleResult();
		return totalCount;
	}

	/** 
	* @Method Name	: getSearchAssociateMovie 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public Map<String, Object> getSearchAssociateMovie(String search) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SearchVO> cq = cb.createQuery(SearchVO.class);
		Root<SearchVO> root = cq.from(SearchVO.class);

		Predicate p = cb.like(root.get("title"), "%"+search+"%");
		
		cq.distinct(true).multiselect(root.get("movie_id"),root.get("actor_name")).where(p).orderBy(cb.asc(root.get("actor_name")));
		TypedQuery<SearchVO> actor_tq = em.createQuery(cq);
		List<SearchVO> actor_list = actor_tq.getResultList();
		
		cq.distinct(true).multiselect(root.get("movie_id"),root.get("director_name")).where(p).orderBy(cb.asc(root.get("director_name")));
		TypedQuery<SearchVO> director_tq = em.createQuery(cq);
		List<SearchVO> director_list = director_tq.getResultList();
		
		cq.distinct(true).multiselect(root.get("movie_id"),root.get("movie_genre")).where(p).orderBy(cb.asc(root.get("movie_genre")));
		TypedQuery<SearchVO> genre_tq = em.createQuery(cq);
		List<SearchVO> genre_list = genre_tq.getResultList();
		
		Map<String,Object> map = new HashMap<>();
		map.put("actor", actor_list);
		map.put("director", director_list);
		map.put("genre", genre_list);
		
		return map;
	}

	
	/*------------Actor Search------------*/
	
	/** 
	* @Method Name	: getSearchActor 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public List<ActorVO> getSearchActor(String search) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ActorVO> cq = cb.createQuery(ActorVO.class);
		Root<ActorVO> root = cq.from(ActorVO.class);
		Predicate p = cb.like(root.get("actor_name"), "%"+search+"%");
		cq.distinct(true).multiselect(root.get("actor_name"),
									  root.get("actor_age"),
									  root.get("actor_photo")).where(p);
		Order order = cb.asc(root.get("actor_name"));
		cq.orderBy(order);
		TypedQuery<ActorVO> tq = em.createQuery(cq);
		List<ActorVO> list = tq.getResultList();
		
		return list;
		
	}

	/** 
	* @Method Name	: getListPageActor 
	* @Method 설명	: 
	* @param search
	* @param criteria
	* @return 
	*/
	@Override
	public List<ActorVO> getListPageActor(String search, Criteria criteria) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ActorVO> cq = cb.createQuery(ActorVO.class);
		Root<ActorVO> root = cq.from(ActorVO.class);
		Predicate p = cb.like(root.get("actor_name"), "%"+search+"%");
		cq.distinct(true).multiselect(root.get("actor_name"),
									  root.get("actor_age"),
									  root.get("actor_photo")).where(p);
		Order order = cb.asc(root.get("actor_name"));
		cq.orderBy(order);
		TypedQuery<ActorVO> tq = em.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());
		List<ActorVO> pageList = tq.getResultList();

		return pageList;
		
	}

	/** 
	* @Method Name	: getTotalCountActor 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public long getTotalCountActor(String search) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ActorVO> root = cq.from(ActorVO.class);
		Predicate p = cb.like(root.get("actor_name"), "%"+search+"%");
		cq.select(cb.countDistinct(root.get("actor_name")));
		cq.where(p);
		TypedQuery<Long> tq = em.createQuery(cq);
		long  totalCount = tq.getSingleResult();
		
		return totalCount;
		
	}

	/** 
	* @Method Name	: getSearchAssociateMovieWithActor 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public List<SearchVO> getSearchAssociateMovieWithActor(String search) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SearchVO> cq = cb.createQuery(SearchVO.class);
		Root<SearchVO> root = cq.from(SearchVO.class);
		Predicate p = cb.like(root.get("actor_name"), "%"+search+"%");
		Order order = cb.asc(root.get("actor_name"));
		cq.distinct(true).multiselect(root.get("movie_id"),root.get("title"),
					   				  root.get("actor_name")).where(p).orderBy(order);
		
		TypedQuery<SearchVO> tq = em.createQuery(cq);
		List<SearchVO> list = tq.getResultList();
		
		return list;
		
	}

	
	/*------------Director Search------------*/
	
	/** 
	* @Method Name	: getSearchDirector 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public List<DirectorVO> getSearchDirector(String search) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DirectorVO> cq = cb.createQuery(DirectorVO.class);
		Root<DirectorVO> root = cq.from(DirectorVO.class);
		Predicate p = cb.like(root.get("director_name"), "%"+search+"%");
		cq.distinct(true).multiselect(root.get("director_name"),
									  root.get("director_age"),
									  root.get("director_photo")).where(p);
		Order order = cb.asc(root.get("director_name"));
		cq.orderBy(order);
		TypedQuery<DirectorVO> tq = em.createQuery(cq);
		List<DirectorVO> list = tq.getResultList();

		return list;
		
	}

	/** 
	* @Method Name	: getListPageDirector 
	* @Method 설명	: 
	* @param search
	* @param criteria
	* @return 
	*/
	@Override
	public List<DirectorVO> getListPageDirector(String search, Criteria criteria) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DirectorVO> cq = cb.createQuery(DirectorVO.class);
		Root<DirectorVO> root = cq.from(DirectorVO.class);
		Predicate p = cb.like(root.get("director_name"), "%"+search+"%");
		cq.distinct(true).multiselect(root.get("director_name"),
									  root.get("director_age"),
									  root.get("director_photo")).where(p);
		Order order = cb.asc(root.get("director_name"));
		cq.orderBy(order);
		TypedQuery<DirectorVO> tq = em.createQuery(cq).setFirstResult(criteria.getStartPage()).setMaxResults(criteria.getArticlePerPage());
		List<DirectorVO> pageList = tq.getResultList();

		return pageList;
		
	}

	/** 
	* @Method Name	: getTotalCountDirector 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public long getTotalCountDirector(String search) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<DirectorVO> root = cq.from(DirectorVO.class);
		Predicate p = cb.like(root.get("director_name"), "%"+search+"%");
		cq.select(cb.countDistinct(root.get("director_name")));
		cq.where(p);
		TypedQuery<Long> tq = em.createQuery(cq);
		long  totalCount = tq.getSingleResult();
		
		return totalCount;
		
	}

	/** 
	* @Method Name	: getSearchAssociateMovieWithDirector 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	public List<SearchVO> getSearchAssociateMovieWithDirector(String search) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SearchVO> cq = cb.createQuery(SearchVO.class);
		Root<SearchVO> root = cq.from(SearchVO.class);
		Predicate p = cb.like(root.get("director_name"), "%"+search+"%");
		Order order = cb.asc(root.get("director_name"));
		cq.distinct(true).multiselect(root.get("movie_id"),root.get("title"),
					   				  root.get("director_name")).where(p).orderBy(order);
		
		TypedQuery<SearchVO> tq = em.createQuery(cq);
		List<SearchVO> list = tq.getResultList();
		
		return list;
		
	}

}

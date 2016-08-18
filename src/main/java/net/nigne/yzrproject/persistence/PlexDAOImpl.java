package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.PlexVO;

@Repository
public class PlexDAOImpl implements PlexDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PlexVO> getList(String plexNum, String theaterId) {
		// TODO Auto-generated method stub
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<PlexVO> mainQuery = cb.createQuery(PlexVO.class);
				Root<PlexVO> mainQueryroot = mainQuery.from(PlexVO.class);
				
				// select * from theater where theater_area = '지역이름'
				mainQuery.select(mainQueryroot);
				mainQuery.where(cb.equal(mainQueryroot.get("plex_number"), plexNum), cb.equal(mainQueryroot.get("theater_id"), theaterId));
				
				TypedQuery<PlexVO> tq = entityManager.createQuery(mainQuery);
				List<PlexVO> list = tq.getResultList();
				
				
				return list;
	}

}

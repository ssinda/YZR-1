package net.nigne.yzrproject.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.PurchaseVO;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void payPersist(PurchaseVO vo) throws Exception{
		entityManager.persist(vo);
	}

}

package net.nigne.yzrproject.service;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.persistence.PurchaseDAO;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseDAO dao;
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void payPersist(PurchaseVO vo) throws Exception{
		dao.payPersist(vo);
	}

}

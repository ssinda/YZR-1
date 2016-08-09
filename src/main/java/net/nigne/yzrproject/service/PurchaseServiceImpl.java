package net.nigne.yzrproject.service;

<<<<<<< HEAD
import java.util.List;
=======
import javax.persistence.PersistenceContext;
>>>>>>> master

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import net.nigne.yzrproject.domain.Criteria;
=======
>>>>>>> master
import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.persistence.PurchaseDAO;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseDAO dao;
	
<<<<<<< HEAD
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public long getTotalCount(String member_id) {
		// TODO Auto-generated method stub
		return dao.getTotalCount(member_id);
	}


	@Transactional(rollbackFor = { Exception.class })
	@Override
	public List<PurchaseVO> getListPage(String member_id, Criteria criteria) {
		// TODO Auto-generated method stub
		return dao.getListPage(member_id, criteria);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public void delete_Store(int no) {
		// TODO Auto-generated method stub
		dao.delete_Store(no);
=======
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void payPersist(PurchaseVO vo) throws Exception{
		dao.payPersist(vo);
>>>>>>> master
	}

}

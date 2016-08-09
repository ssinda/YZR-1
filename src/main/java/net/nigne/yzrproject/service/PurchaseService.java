package net.nigne.yzrproject.service;

<<<<<<< HEAD
import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.PurchaseVO;

public interface PurchaseService {
	public long getTotalCount(String member_id);
	public List<PurchaseVO> getListPage(String member_id, Criteria criteria);
	public void delete_Store(int no);

=======
import net.nigne.yzrproject.domain.PurchaseVO;

public interface PurchaseService {
	public void payPersist(PurchaseVO vo) throws Exception;
>>>>>>> master
}

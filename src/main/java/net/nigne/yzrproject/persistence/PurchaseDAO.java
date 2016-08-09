package net.nigne.yzrproject.persistence;

<<<<<<< HEAD
import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.PurchaseVO;
import net.nigne.yzrproject.domain.ReviewVO;

public interface PurchaseDAO {
	public long getTotalCount(String member_id);
	public List<PurchaseVO> getListPage(String member_id, Criteria criteria);
	public void delete_Store(int no);

=======
import net.nigne.yzrproject.domain.PurchaseVO;

public interface PurchaseDAO {
	public void payPersist(PurchaseVO vo) throws Exception;
>>>>>>> master
}

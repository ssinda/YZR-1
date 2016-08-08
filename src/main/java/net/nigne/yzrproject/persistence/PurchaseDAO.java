package net.nigne.yzrproject.persistence;

import net.nigne.yzrproject.domain.PurchaseVO;

public interface PurchaseDAO {
	public void payPersist(PurchaseVO vo) throws Exception;
}

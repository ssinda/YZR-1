package net.nigne.yzrproject.service;

import net.nigne.yzrproject.domain.PurchaseVO;

public interface PurchaseService {
	public void payPersist(PurchaseVO vo) throws Exception;
}

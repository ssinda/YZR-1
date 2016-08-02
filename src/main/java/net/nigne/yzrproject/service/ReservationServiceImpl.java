package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.ReservationVO;
import net.nigne.yzrproject.persistence.ReservationDAO;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<ReservationVO> getReservation_list(String member_id) {
		return dao.getReservation_list(member_id);
	}

	/** 
	* @Method Name	: getReservation 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getReservation(String member_id) {

		return dao.getReservation(member_id);
	}

	/** 
	* @Method Name	: getReservationTotal 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	@Transactional(readOnly=true)
	public long getReservationTotal(String member_id) {
		
		return dao.getReservationTotal(member_id);
	}

	/** 
	* @Method Name	: getReservationPage 
	* @Method 설명	: 
	* @param member_id
	* @param criteria
	* @return 
	*/
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getReservationPage(String member_id, Criteria criteria) {
		
		return dao.getReservationPage(member_id, criteria);
	}

}

package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}

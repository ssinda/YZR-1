package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.SeatVO;
import net.nigne.yzrproject.persistence.SeatDAO;

@Service
public class SeatServiceImpl implements SeatService {
	
	@Autowired
	private SeatDAO dao;

	@Override
	public List<SeatVO> getList(String plexNum, String startTime) {
		// TODO Auto-generated method stub
		return dao.getList(plexNum, startTime);
	}

	@Override
	public List<SeatVO> getIndex(String plexNum, String startTime) {
		// TODO Auto-generated method stub
		return dao.getIndex(plexNum, startTime);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void updateReservation(int SeatNo) {
		dao.updateReservation(SeatNo);	
	}

	@Override
	public List<Integer> getPrimary(String theaterId, String plexNum, String seat1, String seat2, String seat3,
								 String seat4, String seat5, String seat6, String seat7, String seat8) {
		// TODO Auto-generated method stub
		return dao.getPrimary(theaterId, plexNum, seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8);
	}

	@Override
	public List<String> getReservationExist(String theaterId, String plexNum, String seat1, String seat2, String seat3,
			String seat4, String seat5, String seat6, String seat7, String seat8) {
		// TODO Auto-generated method stub
		return dao.getReservationExist(theaterId, plexNum, seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void reservationCancel(int SeatNo) {
		// TODO Auto-generated method stub
		dao.reservationCancel(SeatNo);
	}

}

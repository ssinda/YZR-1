package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.ReservationVO;

public interface ReservationService {
	public List<ReservationVO> getReservation_list(String member_id);
}

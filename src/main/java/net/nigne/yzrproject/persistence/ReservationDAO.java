package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.ReservationVO;

public interface ReservationDAO {
	public List<ReservationVO> getReservation_list(String member_id);
}

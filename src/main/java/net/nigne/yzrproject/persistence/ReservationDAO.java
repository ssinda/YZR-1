package net.nigne.yzrproject.persistence;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.ReservationVO;

public interface ReservationDAO {
	public List<ReservationVO> getReservation_list(String member_id);
	public Map<String,Object> getReservation(String member_id);
	public Map<String,Object> getReservationPage(String member_id, Criteria criteria);
	public long getReservationTotal(String member_id);
	public void reservationCancel(String reservation_code);
}

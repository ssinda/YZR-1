/** 
*
*/
package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;

/** 
* @FileName : UserCouponDAO.java 
* @Package  : net.nigne.yzrproject.persistence 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
public interface UserCouponDAO {
	public long getCouponTotal(String member_id);
	public List<CouponVO> getCouponList(Criteria cri, String member_id);

}

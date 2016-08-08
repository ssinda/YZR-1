/** 
*
*/
package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;

/** 
* @FileName : UserCouponService.java 
* @Package  : net.nigne.yzrproject.service 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
public interface UserCouponService {
	public long getCouponTotal(String member_id);
	public List<CouponVO> getCouponList(Criteria cri, String member_id);
}

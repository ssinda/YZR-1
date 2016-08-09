/** 
*
*/
package net.nigne.yzrproject.persistence;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.SearchVO;

/** 
* @FileName : SerachDAO.java 
* @Package  : net.nigne.yzrproject.persistence 
* @Date     : 2016. 7. 21. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
public interface SearchDAO {
	
	/*------------Movie Search------------*/
	public List<MovieVO> getSearch(String search);
	public List<MovieVO> getListPage(String search,Criteria criteria);
	public long getTotalCount(String search);
	public Map<String,Object> getSearchAssociateMovie(String search);
	
	/*------------Actor Search------------*/
	public List<ActorVO> getSearchActor(String search);
	public List<ActorVO> getListPageActor(String search,Criteria criteria);
	public long getTotalCountActor(String search);
	public List<SearchVO> getSearchAssociateMovieWithActor(String search);
	
	/*------------Director Search------------*/
	public List<DirectorVO> getSearchDirector(String search);
	public List<DirectorVO> getListPageDirector(String search,Criteria criteria);
	public long getTotalCountDirector(String search);
	public List<SearchVO> getSearchAssociateMovieWithDirector(String search);
	
}

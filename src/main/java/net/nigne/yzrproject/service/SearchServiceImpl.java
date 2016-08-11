/** 
*
*/
package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.SearchVO;
import net.nigne.yzrproject.persistence.SearchDAO;

/** 
* @FileName : SearchServiceImpl.java 
* @Package  : net.nigne.yzrproject.service 
* @Date     : 2016. 7. 21. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDAO dao;
	
	/*------------Movie Search------------*/
	/** 
	* @Method Name	: getSearch 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<MovieVO> getSearch(String search) {
		
		return dao.getSearch(search);
	}

	/** 
	* @Method Name	: getListPage 
	* @Method 설명	: 
	* @param search
	* @param criteria
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<MovieVO> getListPage(String search, Criteria criteria) {
		
		return dao.getListPage(search, criteria);
	}

	/** 
	* @Method Name	: getTotalCount 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public long getTotalCount(String search) {
		
		return dao.getTotalCount(search);
	}

	/** 
	* @Method Name	: getSearchAssociateMovie 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getSearchAssociateMovie(String search) {
		
		return dao.getSearchAssociateMovie(search);
	}

	
	/*------------Actor Search------------*/
	
	/** 
	* @Method Name	: getSearchActor 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<ActorVO> getSearchActor(String search) {
		
		return dao.getSearchActor(search);
	}

	/** 
	* @Method Name	: getListPageActor 
	* @Method 설명	: 
	* @param search
	* @param criteria
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<ActorVO> getListPageActor(String search, Criteria criteria) {
		
		return dao.getListPageActor(search, criteria);
	}

	/** 
	* @Method Name	: getTotalCountActor 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public long getTotalCountActor(String search) {
		
		return dao.getTotalCountActor(search);
	}

	/** 
	* @Method Name	: getSearchAssociateMovieWithActor 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<SearchVO> getSearchAssociateMovieWithActor(String search) {
		
		return dao.getSearchAssociateMovieWithActor(search);
	}

	
	/*------------Director Search------------*/
	
	/** 
	* @Method Name	: getSearchDirector 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<DirectorVO> getSearchDirector(String search) {
		
		return dao.getSearchDirector(search);
	}

	/** 
	* @Method Name	: getListPageDirector 
	* @Method 설명	: 
	* @param search
	* @param criteria
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<DirectorVO> getListPageDirector(String search, Criteria criteria) {
		
		return dao.getListPageDirector(search, criteria);
	}

	/** 
	* @Method Name	: getTotalCountDirector 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public long getTotalCountDirector(String search) {
		
		return dao.getTotalCountDirector(search);
	}

	/** 
	* @Method Name	: getSearchAssociateMovieWithDirector 
	* @Method 설명	: 
	* @param search
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public List<SearchVO> getSearchAssociateMovieWithDirector(String search) {
		
		return dao.getSearchAssociateMovieWithDirector(search);
	}

}

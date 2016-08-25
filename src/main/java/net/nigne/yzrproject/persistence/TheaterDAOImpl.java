package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.TheaterVO;

@Repository
public class TheaterDAOImpl implements TheaterDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TheaterVO> getTheater(String location) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> cq = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> root = cq.from(TheaterVO.class);
		
		cq.where(cb.equal(root.get("theater_area"), location));
		cq.orderBy(cb.asc(root.get("theater_name")));
		TypedQuery<TheaterVO> tq = entityManager.createQuery(cq);
		List<TheaterVO> theater_list = tq.getResultList();
		return theater_list;
	}
	
	@Override
	public List<TheaterVO> getList(String theater) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> mainQuery = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> mainQueryroot = mainQuery.from(TheaterVO.class);
		
		// select * from theater where theater_area = '지역이름'
		mainQuery.select(mainQueryroot);
		mainQuery.where(cb.equal(mainQueryroot.get("theater_area"), theater));
		
		TypedQuery<TheaterVO> tq = entityManager.createQuery(mainQuery);
		List<TheaterVO> list = tq.getResultList();
		
		return list;
	}

	@Override
	public List<Long> getLocalTheaterNum() {
		int code = 1;
		List<Long> list = new ArrayList<Long>();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> mainQuery = cb.createQuery(Long.class);
		Root<TheaterVO> mainQueryroot = mainQuery.from(TheaterVO.class);
		
		while(codeNum()>=code){
			mainQuery.select(cb.count(mainQueryroot)).orderBy(cb.asc(mainQueryroot.get("area_code")));
			mainQuery.where(cb.equal(mainQueryroot.get("area_code"), code));
			
			TypedQuery<Long> tq = entityManager.createQuery(mainQuery);
			list.add(tq.getSingleResult());
			
			code++;
		}
		
		return list;
	}

	@Override
	public List<TheaterVO> getLocal() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> mainQuery = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> mainQueryroot = mainQuery.from(TheaterVO.class);
		
		mainQuery.multiselect(mainQueryroot.get("theater_area"), mainQueryroot.get("area_code")).distinct(true).orderBy(cb.asc(mainQueryroot.get("area_code")));
		
		TypedQuery<TheaterVO> tq = entityManager.createQuery(mainQuery);
		List<TheaterVO> list = tq.getResultList();
		
		return list;
	}
	
	private int codeNum(){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> mainQuery = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> mainQueryroot = mainQuery.from(TheaterVO.class);
		
		mainQuery.select(mainQueryroot.get("theater_area")).distinct(true);
		
		TypedQuery<TheaterVO> tq = entityManager.createQuery(mainQuery);
		List<TheaterVO> list = tq.getResultList();
		
		int codeNum = list.size();	
		
		return codeNum;
	}

	@Override
	public List<TheaterVO> getTheaterId(String theaterName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> mainQuery = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> mainQueryroot = mainQuery.from(TheaterVO.class);
		
		mainQuery.select(mainQueryroot);
		mainQuery.where(cb.equal(mainQueryroot.get("theater_name"), theaterName));
		
		TypedQuery<TheaterVO> tq = entityManager.createQuery(mainQuery);
		List<TheaterVO> list = tq.getResultList();

		return list;
	}

	@Override
	public List<TheaterVO> getTheaterList(String theaterId) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> mainQuery = cb.createQuery(TheaterVO.class);
		Root<TheaterVO> mainQueryroot = mainQuery.from(TheaterVO.class);
		
		mainQuery.select(mainQueryroot);
		mainQuery.where(cb.equal(mainQueryroot.get("theater_id"), theaterId));
		
		TypedQuery<TheaterVO> tq = entityManager.createQuery(mainQuery);
		List<TheaterVO> list = tq.getResultList();

		return list;
	}

}

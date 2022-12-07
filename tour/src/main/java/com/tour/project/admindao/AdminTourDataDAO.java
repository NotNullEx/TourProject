package com.tour.project.admindao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.TourVO;
import com.tour.project.common.vo.PageCriteriaVO;
@Repository
public class AdminTourDataDAO {

	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int tourInsert(TourVO vo) throws Exception{
		int result = -1;
		result = sqltemplate.insert("tour.tourInsert", vo);
		return result;
		
	}
	public int tourInsert(HashMap<String, Object> map) throws Exception{
		int result = -1;
		result = sqltemplate.insert("tour.tourDataInsert", map);
		return result;
		
	}
	
	public int getToatal() throws Exception{
		int getToatal = sqltemplate.selectOne("tour.getTotal");
		return getToatal;
	}
	
	public List<TourVO> tourList(PageCriteriaVO cri) throws Exception{
		List<TourVO> tourList = sqltemplate.selectList("tour.selecttourList",cri);
		return tourList;
	}
	
	public List<TourVO> tourOneList(String tour_seq)throws Exception{
		List<TourVO> tourList = sqltemplate.selectList("tour.tourOneList",tour_seq);
		return tourList;
	}
	
	public int tourUpdateData(HashMap<String, Object> map) throws Exception{
		int result = -1;
		result = sqltemplate.update("tour.tourUpdateData",map);
		return result;
	}
	
	public int tourDeleteDate(String seq) throws Exception{
		int result = -1;
		result = sqltemplate.delete("tour.tourDeleteData",seq);
		return result;
	}
	
	public int insUrl(HashMap<String, String> map) throws Exception{
		int result = -1;
		result = sqltemplate.insert("tour.insUrl",map);
		return result;
		
	}
	
	public int chkUrl(HashMap<String, String> map) throws Exception{
		int result = -1;
		result = sqltemplate.selectOne("tour.chkUrl",map);
		return result;
		
	}
	
	public List<TourVO> getTourListAll() throws Exception {
		List<TourVO> list = sqltemplate.selectList("tour.tourListAll");
		
		return list;
	}
	
	public List<HashMap<String, Integer>> memTotal() throws Exception{

		List<HashMap<String, Integer>> list = new ArrayList<HashMap<String,Integer>>();
		list = sqltemplate.selectList("tour.memTotal");

		return list;
	}

	public HashMap<String, Integer> getContentsTotal() throws Exception{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
			map = sqltemplate.selectOne("tour.getContentsTotal");
		return map;
	}
}

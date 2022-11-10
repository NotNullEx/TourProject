package com.tour.project.frontdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.BoardVO;

@Repository
public class FrontBoardDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.insert("board.create",map);
		return result;
	}
	public List<BoardVO> listAll(){
		List<BoardVO> result = new ArrayList<BoardVO>();
		result = sqltemplate.selectList("board.listAll");
		return result;
		
	}
	
	public List<BoardVO> listBySeq(String seq){
		List<BoardVO> result = new ArrayList<BoardVO>();
		result = sqltemplate.selectList("board.listBySeq", seq);
		return result;
		
	}
	
}
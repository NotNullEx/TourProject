package com.tour.project.frontdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.BoardVO;
import com.tour.project.common.vo.PageCriteriaVO;

@Repository
public class FrontBoardDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.insert("board.create",map);
		return result;
	}
	public List<BoardVO> listAll(PageCriteriaVO cri){
		List<BoardVO> result = new ArrayList<BoardVO>();
		result = sqltemplate.selectList("board.listAll", cri);
		return result;
		
	}
	
	public List<BoardVO> listBySeq(String seq){
		List<BoardVO> result = new ArrayList<BoardVO>();
		result = sqltemplate.selectList("board.listBySeq", seq);
		return result;
		
	}
	
	public int edit(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.update("board.update", map);
		return result;
	}
	
	public int deleteOne(String seq) {
		int result = 0;
		result = sqltemplate.delete("board.deleteOne",seq);
		return result;
		
	}
	
	public List<BoardVO> myBoardList(HashMap<String, Object> map) throws Exception {
		return sqltemplate.selectList("board.myBoardList", map);
	}
	
	public int getMyBoardTotal(int seq) throws Exception {
		return sqltemplate.selectOne("board.getMyBoardTotal",seq);
	}
}

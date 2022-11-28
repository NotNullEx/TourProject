package com.tour.project.frontservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.BoardVO;

public interface FrontBoardService {
	public int create(Map<String, Object> map);
	public List<BoardVO> listAll();
	public List<BoardVO> listBySeq(String seq);
	public int edit(Map<String, Object> map);
	public Object deleteOne(String seq);
	public List<BoardVO> myBoardList(HashMap<String, Object> map) throws Exception;
	public int getMyBoardTotal(int seq) throws Exception;
}

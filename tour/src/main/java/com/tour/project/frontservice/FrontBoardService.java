package com.tour.project.frontservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.BoardVO;

public interface FrontBoardService {
	public int create(Map<String, Object> map);
	public List<BoardVO> listAll();
	public List<BoardVO> listBySeq(String seq);
}

package com.tour.project.frontservice;

import java.util.List;
import java.util.Map;

import com.tour.project.frontvo.ComentsVO;

public interface FrontComentsService {
	public int create(Map<String, Object> map) throws Exception;
	public List<ComentsVO> comentsByBoard(String board_seq) throws Exception;
	public int deleteComentsWithSeq(String coment_seq) throws Exception;
	public int reviseComents(Map<String, Object> map) throws Exception;
}

package com.tour.project.frontservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.frontdao.FrontComentsDao;
import com.tour.project.frontvo.ComentsVO;
@Service
public class FrontComentsServiceImpl implements FrontComentsService {
	@Autowired
	private FrontComentsDao comentsDao;
	
	@Override
	public int create(Map<String, Object> map) throws Exception {
		return comentsDao.create(map);
	}

	@Override
	public List<ComentsVO> comentsByBoard(String board_seq) throws Exception {
		return comentsDao.comentsByBoard(board_seq);
	}

	@Override
	public int deleteComentsWithSeq(String coment_seq) throws Exception {
		return comentsDao.deleteComentsWithSeq(coment_seq);
	}

}

package com.tour.project.frontservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminBoardDao;
import com.tour.project.adminvo.BoardVO;
import com.tour.project.frontdao.FrontBoardDao;
@Service
public class FrontBoardServiceImpl implements FrontBoardService {
	@Autowired
	private FrontBoardDao createDao;
	
	@Override
	public int create(Map<String, Object> map) {
		int result = createDao.create(map);	
		return result;
	}
	
	@Override
	public List<BoardVO> listAll(){
		List<BoardVO> result = createDao.listAll();
		return result;
		
	}

	@Override
	public List<BoardVO> listBySeq(String seq) {
		List<BoardVO> result = createDao.listBySeq(seq);
		return result;
	}

}

package com.tour.project.frontservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminBoardDao;
import com.tour.project.adminvo.BoardVO;
import com.tour.project.common.vo.PageCriteriaVO;
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
	public List<BoardVO> listAll(PageCriteriaVO cri){
		List<BoardVO> result = createDao.listAll(cri);
		return result;
		
	}

	@Override
	public List<BoardVO> listBySeq(String seq) {
		List<BoardVO> result = createDao.listBySeq(seq);
		return result;
	}

	@Override
	public int edit(Map<String, Object> map) {
		int result = 0;
		result = createDao.edit(map);
		return result;
	}

	@Override
	public Object deleteOne(String seq) {
		int result = 0;
		result = createDao.deleteOne(seq);
		return result;
	}

	@Override
	public List<BoardVO> myBoardList(HashMap<String, Object> map) throws Exception {
		return createDao.myBoardList(map);
	}

	@Override
	public int getMyBoardTotal(int seq) throws Exception {
		// TODO Auto-generated method stub
		return createDao.getMyBoardTotal(seq);
	}

}

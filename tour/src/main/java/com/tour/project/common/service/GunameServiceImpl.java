package com.tour.project.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.common.dao.GunameDao;
import com.tour.project.common.vo.GunameVO;
@Service
public class GunameServiceImpl implements GunameService {

	@Autowired
	private GunameDao gunameDao;
	
	@Override
	public List<GunameVO> gunameList() throws Exception {
		return gunameDao.gunameList();
	}

}

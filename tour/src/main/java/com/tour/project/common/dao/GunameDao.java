package com.tour.project.common.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.common.vo.GunameVO;

@Repository
public class GunameDao {
	
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public List<GunameVO> gunameList() throws Exception {
		return sqltemplate.selectList("guname.gunameList");
	}
}

package com.tour.project.admindao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.AdminVO;
@Repository
public class CreateAdminDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		int seq = 0;
		List<Integer> listSeq = sqltemplate.selectList("admin.select_seq");
		for (int i = 0; i < listSeq.toArray().length; i++) {
			seq = i+1;
		}
		map.put("seq", seq);
		return sqltemplate.insert("admin.create", map);
	}
}
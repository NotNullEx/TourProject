package com.tour.project.frontdao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.frontvo.ComentsVO;

@Repository
public class FrontComentsDao {
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) throws Exception {
		return sqltemplate.insert("coments.create", map);
	}
	
	public List<ComentsVO> comentsByBoard(String board_seq) throws Exception {
		return sqltemplate.selectList("coments.comentsByBoard", board_seq);
	}
	
	public int deleteComentsWithSeq(String coment_seq) throws Exception {
		return sqltemplate.delete("coments.deleteComentsWithSeq", coment_seq);
	}
}

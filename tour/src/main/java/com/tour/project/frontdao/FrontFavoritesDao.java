package com.tour.project.frontdao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.TourVO;

@Repository
public class FrontFavoritesDao {
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) throws Exception {
		return sqltemplate.insert("favorites.create", map);
	}
	
	public List<TourVO> tourListByFavorites(int seq) throws Exception {
		return sqltemplate.selectList("favorites.tourListByFavorites", seq);
	}
	
	public int cancelFavorites(Map<String, Object> map) throws Exception {
		return sqltemplate.delete("favorites.cancelFavorites", map);
	}
}

package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.Video;

public class VideoDAOImpl extends AbstractDAO<Video> implements VideoDAO {

	@Override
	public Video findByID(Integer ID) {
		return super.findbyID(Video.class, ID);
	}

	@Override
	public Video findByHref(String href) {
		String sql = "SELECT o FROM Video o WHERE o.href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return super.findAll(Video.class, true,pageNumber,pageSize);
	}

}

package com.poly.dao.impl;

import java.util.List;
import java.util.Map;

import com.poly.constant.NamedStored;
import com.poly.dao.AbstractDAO;
import com.poly.dao.UserDAO;
import com.poly.entity.User;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	@Override
	public User findById(Integer id) {
		return super.findbyID(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "Select o FROM User o WHERE o.email =?0";
		return super.findOne(User.class, sql,email);
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = "SElect o FROM User o WHERE o.username =?0";
		return super.findOne(User.class, sql,username);
	}

	@Override
	public User findByIdAndPassWord(String username, String password) {
		// TODO Auto-generated method stub
		String sql  = "SELECT o FROM User o WHERE o.username = ?0 and o.password = ?1";
		return super.findOne(User.class, sql, username,password);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true, pageNumber, pageSize);
	}

	@Override
	public User create(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params) {
		return super.callStored(NamedStored.FIND_USERS_LIKED_VIDEO_BY_VIDEO_HREF, params);
	}
	
}

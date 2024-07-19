package com.poly.dao;
import java.util.List;
import java.util.Map;

import com.poly.entity.User;
public interface UserDAO {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUserName(String username);
	User findByIdAndPassWord(String username,String password);
	List<User> findAll();
	List<User> findAll(int pageNumber,int pageSize);
	User create(User entity);
	User update(User entity);
	User delete(User entity);
	List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params);
}

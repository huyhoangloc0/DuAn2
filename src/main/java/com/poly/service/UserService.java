package com.poly.service;

import java.util.List;

import com.poly.dao.dto.UserDto;
import com.poly.entity.User;

public interface UserService {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUserName(String username);
	User Login(String username,String password);
	User RestPass(String email);
	
	List<User> findAll();
	List<User> findAll(int pageNumber,int pageSize);
	User create(String username,String password,String email);
	User update(User entity);
	User delete(String username);
	List<UserDto> findUsersLikedVideoByVideoHref(String href);
	
}

package com.poly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poly.dao.UserDAO;
import com.poly.dao.dto.UserDto;
import com.poly.dao.impl.UserDAOImpl;
import com.poly.entity.User;
import com.poly.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO dao ;
	public UserServiceImpl() {
		dao = new UserDAOImpl();
	}
	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserName(username);
	}

	@Override
	public User Login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByIdAndPassWord(username, password);
	}

	@Override
	public User RestPass(String email) {
		User existUser = findByEmail(email);
		if(existUser != null) {
			String newPass = String.valueOf((int)(Math.random()*((9999-1000)+1))+1000);
			existUser.setPassword(newPass);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User create(String username, String password, String email) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		// TODO Auto-generated method stub
		User user = dao.findByUserName(username);
		user.setIsActive(false);
		return dao.update(user);
	}
	@Override
	public List<UserDto> findUsersLikedVideoByVideoHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href);
		List<User> users = dao.findUsersLikedVideoByVideoHref(params);
		List<UserDto> result = new ArrayList<>();
		users.forEach(user -> {
			UserDto dto = new UserDto();
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			result.add(dto);
		});
		return result;
	}
	
}

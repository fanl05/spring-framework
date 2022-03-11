package com.ryland.spring.service;

import com.ryland.spring.dao.UserDao;
import lombok.Setter;

/**
 * @author Ryland
 */
public class UserServiceImpl implements UserService {

	@Setter
	private UserDao userDao;

	@Override
	public void register() {
		userDao.save();
	}

}

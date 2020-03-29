package com.zhxx.admin.server.service;

import com.zhxx.admin.server.dto.UserDto;
import com.zhxx.admin.server.model.User;

public interface UserService {

	User saveUser(UserDto userDto);
	User saveTeacherUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

	User deleteUser(long id);

//	User updateUserStatus(Long id);

}

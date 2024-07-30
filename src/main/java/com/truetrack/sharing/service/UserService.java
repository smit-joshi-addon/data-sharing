package com.truetrack.sharing.service;

import java.util.List;

import com.truetrack.sharing.entity.Users;

public interface UserService {

	public Users createUser(Users user);

	public Users updateUser(Long userId, Users user);

	public List<Users> getUsers();
	
	public Users getUser(String username);

	public void removeUser(Long userId);

}

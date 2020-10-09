package com.project.ifaces;

import com.project.entity.RegisteredUser;

public interface RegisteredUserDAO extends DAO<RegisteredUser> {
	
	String getHashPassword(String password);
}

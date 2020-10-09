package com.project.ifaces;

import com.project.entity.User;

public interface UserDAO extends DAO<User> {
	
	boolean exists(String userEmail, String userType);
	boolean registeredUserExists(String userEmail);
	boolean updateRegistrationStatus(String userEmail);
}

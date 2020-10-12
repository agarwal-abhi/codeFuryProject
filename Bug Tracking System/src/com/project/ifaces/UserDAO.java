package com.project.ifaces;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Madhura Satao
 * @author Aishwarya Thakur
 * @author Gayatri Walve
 *User DAO 
 * 
 */

import com.project.entity.User;

public interface UserDAO extends DAO<User> {
	
	boolean exists(String userEmail, String userType);			//method to check if user exists in database
	boolean registeredUserExists(String userEmail);				//method to check if user is registered
	boolean updateRegistrationStatus(String userEmail);         //method to confirm user registration
	public Timestamp getLastLogin(String email) throws SQLException;  //method to get lastLogged in time and date

}

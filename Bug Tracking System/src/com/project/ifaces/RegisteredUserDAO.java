package com.project.ifaces;

/**
 * @author Madhura Satao
 * @author Aishwarya Thakur
 * 
 *Registered User DAO 
 * 
 */

import com.project.entity.RegisteredUser;

public interface RegisteredUserDAO extends DAO<RegisteredUser> {
	
	String getHashPassword(String password);			//generate secure hashcode for password
}

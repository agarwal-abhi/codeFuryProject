package com.project.daos;

/**
 * @author Madhura Satao
 * @author Aishwarya Thakur
 * 
 * Registered User DAO Implementation
 * 
 * This service class performs the CRUD operation on the RegisteredUser Table along with the functionalities of verifying the user before 
 * login and generating a hashed password for the user.
 *  
 * 
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.RegisteredUser;
import com.project.entity.User;
import com.project.ifaces.RegisteredUserDAO;
import com.project.utils.ConnectionUtility;


public class RegisteredUserDAOImpl implements RegisteredUserDAO{

	private Connection derbyConnection;
	private List<RegisteredUser> registeredUserList;
	
	public RegisteredUserDAOImpl() {
		super();
		registeredUserList = new ArrayList<RegisteredUser>();
		this.derbyConnection=ConnectionUtility.getDerbyConnection();
	}

	@Override
	public boolean add(RegisteredUser entity) {				//add to registered user table
		
		int isInserted = 0;
		boolean isUpdated = false;
		boolean isDone = false;
		UserDAOImpl userDao = new UserDAOImpl();
		
		String sql="insert into RegisteredUser values(?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			this.derbyConnection.setAutoCommit(true);
			try
			{
				pstmt=this.derbyConnection.prepareStatement(sql);
				pstmt.setString(1,entity.getUserEmail());
				pstmt.setString(2, entity.getUserPassword());
				pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(entity.getLastLogin()));
				isInserted = pstmt.executeUpdate();
				pstmt.close();
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			isUpdated = userDao.updateRegistrationStatus(entity.getUserEmail());
			
			if(isInserted == 1 && isUpdated == true) {
				this.registeredUserList.add(entity);
				this.derbyConnection.commit();
				isDone = true;
			}
			else {
				this.derbyConnection.rollback();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isDone;
	}

	@Override
	public List<RegisteredUser> findAll() {								//get list of users
		
		String sql="select * from RegisteredUser";
		PreparedStatement pstmt=null;
		ResultSet result=null;
		
		try {
			pstmt=this.derbyConnection.prepareStatement(sql);
			result = pstmt.executeQuery();
			while(result.next())
			{
				String userEmail = result.getString("userEmail");
				String userPassword = result.getString("userPassword");
				LocalDateTime lastLogin = (result.getTimestamp("lastLogin")).toLocalDateTime();
				
				registeredUserList.clear();
				RegisteredUser registeredUser = new RegisteredUser(userEmail, userPassword, lastLogin);
				registeredUserList.add(registeredUser);
			}
			pstmt.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registeredUserList;
	}
		
	@Override
	public String getHashPassword(String password) 							//generate hashcode for passwords
    { 
        try { 
  
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] passwordBytes = md.digest(password.getBytes()); 
            String hashPassword = new String(passwordBytes); 
            return hashPassword; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
	
public User verifyUser(RegisteredUser entity) {				//authenticate user
		
		User user = new User();
		String sql="select * from UserTable join RegisteredUser on UserTable.userEmail=RegisteredUser.userEmail where RegisteredUser.userEmail=?";
		PreparedStatement pstmt=null;
		ResultSet result=null;
		
		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
			pstmt.setString(1,entity.getUserEmail());
			result = pstmt.executeQuery();
			
			if(result.next()) {
				String userPassword=result.getString("userPassword");
				int userId = result.getInt("userId");
				String userName = result.getString("userName");
				String userType = result.getString("userType");
				boolean isRegistered = result.getBoolean("isRegistered");
				
				String enteredPassword = this.getHashPassword(entity.getUserPassword());
				
				if(enteredPassword.equals(userPassword)) {

					user.setUserId(userId);
					user.setUserName(userName);
					user.setUserEmail(entity.getUserEmail());
					user.setUserType(userType);
					user.setRegistered(isRegistered);
					
				}
				else {
					user = null;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<RegisteredUser> findList(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisteredUser findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}

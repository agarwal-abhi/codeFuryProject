package com.project.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.User;
import com.project.ifaces.UserDAO;
import com.project.utils.ConnectionUtility;

public class UserDAOImpl implements UserDAO{

	private List<User> userList;
	private Connection derbyConnection;
	
	public UserDAOImpl() {
		super();
		this.userList = new ArrayList<User>();
		this.derbyConnection=ConnectionUtility.getDerbyConnection();
	}
	
	@Override
	public boolean add(User entity) throws SQLException {
		String sql="insert into UserTable values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		boolean isInserted = false;
		
		try
		{
			pstmt=this.derbyConnection.prepareStatement(sql);
			pstmt.setLong(1,entity.getUserId());
			pstmt.setString(2, entity.getUserName());
			pstmt.setString(3, entity.getUserEmail());
			pstmt.setString(4, entity.getUserType());
			pstmt.setBoolean(5, false);
			if(pstmt.executeUpdate() == 1) {
				isInserted = true;
				this.userList.add(entity);
			}
			pstmt.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isInserted;
	}

	@Override
	public List<User> findAll() throws SQLException {
	
		String sql="select * from UserTable";
		PreparedStatement pstmt=null;
		ResultSet result=null;
		
		try {
			pstmt=this.derbyConnection.prepareStatement(sql);
			result = pstmt.executeQuery();
			
			while(result.next())
			{
				int userId = result.getInt("userId");
				String userName = result.getString("userName");
				String userEmail = result.getString("userEmail");
				String userType = result.getString("userType");
				boolean isRegistered = result.getBoolean("isRegistered");
				
				userList.clear();
				User user = new User(userId, userName, userEmail, userType, isRegistered);
				userList.add(user);
			}
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;	
	}

	@Override
	public boolean exists(String userEmail, String userType) {
		
		String sql="select * from UserTable where userEmail = ? and userType = ?";
		PreparedStatement pstmt=null;
		boolean exist = false;
		
		try {
			pstmt=this.derbyConnection.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userType);
			ResultSet result=pstmt.executeQuery();
			if(result.next())
			{
				exist = true;
				pstmt.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return exist;	
	}

	@Override
	public boolean registeredUserExists(String userEmail) {
		
		String sql="select isRegistered from UserTable where userEmail = ?";
		PreparedStatement pstmt=null;
		boolean exist = false;
		
		try {
			pstmt=this.derbyConnection.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			ResultSet result=pstmt.executeQuery();
			if(result.next())
			{
				if(result.getBoolean("isRegistered") == true) {
					exist = true;
				}
				pstmt.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return exist;		
	}
	
	@Override
	public boolean updateRegistrationStatus(String userEmail) {
	
		String sql="Update UserTable set isRegistered = ? where userEmail = ?";
		PreparedStatement pstmt=null;
		boolean isUpdated = false;
		
		try {
			pstmt=this.derbyConnection.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, userEmail);
			if(pstmt.executeUpdate() == 1) {
				isUpdated = true;
			}
			pstmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean update(User t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findList(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}	
}

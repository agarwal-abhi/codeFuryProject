package com.project.daos;


/**
 * @author Madhura Satao
 * @author Aishwarya Thakur
 * @author Gayatri Walve
 * User DAO Implementation
 * 
 * This service class performs the CRUD operation on the User Table along with the functionalities of checking if the user details
 * are already present in the database and confirms the registration of the user on first time registration.
 *  
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	//method is used to get one record from the db
	public User getRecords(ResultSet result)
	{
		User user=null;
		try {
			while(result.next())
			{
			int userId = result.getInt("userId");
			String userName = result.getString("userName");
			String userEmail = result.getString("userEmail");
			String userType = result.getString("userType");
			boolean isRegistered = result.getBoolean("isRegistered");
			
			
			 user = new User(userId, userName, userEmail, userType, isRegistered);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	
	public boolean add(User entity) throws SQLException {								//insert single user into table
		String sql="insert into UserTable(userName,userEmail,userType) values(?,?,?)";
		
		// make sure that keys are userName,userEmail and userType exactly this way in JSOn file.
		
		PreparedStatement pstmt = null;
		boolean isInserted = false;
		
		try
		{
			pstmt=this.derbyConnection.prepareStatement(sql);
//			pstmt.setLong(1,entity.getUserId());
			
			//only 3 attributes needed to be inserted userName,userEmail,userRole
			
			pstmt.setString(1, entity.getUserName());
			pstmt.setString(2, entity.getUserEmail());
			pstmt.setString(3, entity.getUserType());
			
//			pstmt.setBoolean(4, false);
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
	public List<User> findAll() throws SQLException {						//get list of users from table
	
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
	public boolean exists(String userEmail, String userType) {						//check if user exists
		
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
	public boolean registeredUserExists(String userEmail) {							//check if user is registered
		
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
	public boolean updateRegistrationStatus(String userEmail) {						//confirm registration
	
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
	public List<User> findList(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	//this method returns the particular user object as we pass id as an argument
	@Override
	public User findById(int id) throws SQLException {
		String sql = "select * from userTable where userId = ?";
		PreparedStatement ps=null;
		User user = null;
		int result=0;
		try {
			ps = this.derbyConnection.prepareStatement(sql);
			ps.setInt(1,id);
			
			ResultSet resultset = ps.executeQuery();
			user = getRecords(resultset);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	//method to get lastLoggedin details of manager
	@Override
	public Timestamp getLastLogin(String email) throws SQLException {
	   //  userList.clear();
		String sql = "select lastLogIn from RegisteredUser where userEmail = ?";
		PreparedStatement ps=null;
		Timestamp time = null;
		int result=0;
		try {
			ps = this.derbyConnection.prepareStatement(sql);
			ps.setString(1,email);
			
			ResultSet resultset = ps.executeQuery();
			if(resultset.next())
			{
				time = resultset.getTimestamp("lastLogIn");
			}
			
			System.out.println("timestamp is:"+time);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return time;
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

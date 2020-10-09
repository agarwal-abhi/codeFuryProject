/**
 * @author Sarthak Mohanty
 *
 * 
 * Implementation methods for the Projects
 * 
 * This module contains methods to add a new project to the database, assign a new developer to the existing project
 *
 */

package com.project.daos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.entity.Project;
import com.project.entity.User;
import com.project.ifaces.ProjectDAO;
import com.project.utils.ConnectionUtility;

public class ProjectDAOImpl implements ProjectDAO {
	

	Connection con = null;
	PreparedStatement psmt= null;
	List<User> userList=null;
	
	
	
	public ProjectDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
		userList=new ArrayList<User>();
		try {
			con= ConnectionUtility.getDerbyConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    // This Method is used to add a new project to the project table in the database
	@Override
	public boolean add(Object t) throws SQLException {
		Project project= (Project) t;
        String sqlQuery= "insert into project values(?,?,?,?,?)";
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, project.getProjectId());
		psmt.setString(2, project.getProjectName());
		psmt.setString(3, project.getProjectDescription());
		psmt.setDate(4, Date.valueOf(project.getStartDate()));
		psmt.setString(5,"in progress");
		
		int flag=psmt.executeUpdate();
		
		return (flag==1)?true:false;
	}

	@Override
	public boolean update(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findList(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	// This Method is used to retrieve a list of all the developers and testers who are not assigned
	// to any project
	public List<User> getUserList(){
		try {
			ResultSet result=null;
			String sql="SELECT userTable.userId,userTable.userName,userTable.userType FROM userTable LEFT JOIN team ON userTable.userId = team.userId WHERE team.userId IS NULL";
			psmt=con.prepareStatement(sql);		
			result= psmt.executeQuery();
			User user=null;
			while(result.next()) {
				user.setUserId(result.getInt("userId"));
				user.setUserName(result.getString("userName"));
				user.setUserType(result.getString("userType"));
				
				this.userList.add(user);
			
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	// This Method is used to assign a developer or a tester to a project
	public boolean assignTo(int userId, int projectId) {
		
      String sqlQuery= "insert into team values(?,?)";
		
		boolean flag=false;
		try {
			psmt= con.prepareStatement(sqlQuery);
			
			psmt.setInt(1, userId);
			psmt.setInt(2, projectId);
		    psmt.executeUpdate();
		    flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}


}

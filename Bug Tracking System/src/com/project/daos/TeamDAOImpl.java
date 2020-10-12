package com.project.daos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.ifaces.TeamDAO;
import com.project.utils.ConnectionUtility;






/**
 * @author Nehal Goyal
 * 
 * A method called findProjects is implemented for finding the list of projects assigned to a particular user.
 *
 */
public class TeamDAOImpl implements TeamDAO {
	
	Connection con = null;
	PreparedStatement psmt= null;
	List<Integer> projectList=null;
	
	




	public TeamDAOImpl() {
		super();
		
		projectList= new ArrayList<>();
		con= ConnectionUtility.getDerbyConnection();
		
		
	
		
		// TODO Auto-generated constructor stub
	}







	@Override
	public List<Integer> findProjectsForUser(int userId) {
		// TODO Auto-generated method stub
		
		projectList.clear();
		String sqlQuery= "select projectId from team where userId=?";
		
		try {
			psmt=con.prepareStatement(sqlQuery);
			
			psmt.setInt(1, userId);
			
			ResultSet resultSet= psmt.executeQuery();
			
			while(resultSet.next())
			{
				projectList.add(resultSet.getInt(1));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return projectList;
	}

}

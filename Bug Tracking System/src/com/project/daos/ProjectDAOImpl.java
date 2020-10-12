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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.project.entity.Project;
import com.project.entity.User;
import com.project.ifaces.ProjectDAO;
import com.project.utils.ConnectionUtility;

public class ProjectDAOImpl implements ProjectDAO {
	Connection con = null;
	PreparedStatement psmt= null;
	List<User> userList=null;
	List<Project> projectList = null;
	List<User>testerList=null;
	List<User>developerList=null;
	
	
	
	public ProjectDAOImpl() {
		super();
		projectList = new ArrayList<Project>();
		userList=new ArrayList<User>();
		testerList=new ArrayList<User>();
		developerList=new ArrayList<User>();
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
		// TODO Auto-generated method stub
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
	public List<Project> findList(int id) throws SQLException {
		projectList.clear();
		String sql = "select * from Project where projectId IN (select projectId from Team where userId =?)";
		PreparedStatement ps=null;
		//List<Integer> projectIdList = null;
	   // int projectId =0;
		int result=0;
		try {
			ps = this.con.prepareStatement(sql);
			ps.setInt(1,id);
			
			ResultSet resultset = ps.executeQuery();
			projectList = getRecords(resultset);
			
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
		
		return projectList;
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
//		public boolean assignTo(int userId, int projectId) {
//			
//	      String sqlQuery= "insert into team values(?,?)";
//			
//			boolean flag=false;
//			try {
//				psmt= con.prepareStatement(sqlQuery);
//				
//				psmt.setInt(1, userId);
//				psmt.setInt(2, projectId);
//			    psmt.executeUpdate();
//			    flag=true;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return flag;
//		}
		
		// This Method is used to retrieve a list of all testers who are not assigned
				// to any project or are assigned to less than 2 projects
//		public List<User> getUserTesterList(){
//			try {
//				ResultSet result=null;
//				String sql="select usertable.userid,usertable.username from usertable left join team on usertable.userid = team.userid group by usertable.userid,usertable.username,usertable.usertype having count(usertable.userid)<2 and usertable.usertype='Tester'";
//				psmt=con.prepareStatement(sql);		
//				result= psmt.executeQuery();
//				User user=null;
//				while(result.next()) {
//					user.setUserId(result.getInt("userId"));
//					user.setUserName(result.getString("userName"));
////					user.setUserType(result.getString("userType"));
//					
//					this.userList.add(user);
//				
//			}} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return userList;
//		}
		
		// This Method is used to retrieve a list of all the developers who are not assigned
				// to any project
//		public List<User> getDeveloperList(){
//			try {
//				ResultSet result=null;
//				String sql="SELECT userTable.userId,userTable.userName,userTable.userType FROM userTable LEFT JOIN team ON userTable.userId = team.userId WHERE team.userId IS NULL and usertable.usertype='Developer'";
//				psmt=con.prepareStatement(sql);		
//				result= psmt.executeQuery();
//				User user=null;
//				while(result.next()) {
//					user.setUserId(result.getInt("userId"));
//					user.setUserName(result.getString("userName"));
//	//				user.setUserType(result.getString("userType"));
//					
//					this.userList.add(user);
//				
//			}} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return userList;
//		}

		public List<Project> getRecords(ResultSet result)
		{
			List<Project> projList=new ArrayList<>();
			Project prj = null;
			try {
				while(result.next())
				{
				int projectId = result.getInt("projectId");
				String projectName = result.getString("projectName");
				String projectDesc = result.getString("projectDescription");
				Date startDate = result.getDate("startDate");
				String projectStatus = result.getString("projectStatus");
				LocalDate date = startDate.toLocalDate();
				
				prj = new Project(projectId, projectName, projectDesc, date, projectStatus);
				projList.add(prj);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return projList;
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
				
				// This Method is used to retrieve a list of all testers who are not assigned
						// to any project or are assigned to less than 2 projects
				public List<User> getUserTesterList(){
					this.testerList.clear();
					try {
						ResultSet result=null;
						String sql="select usertable.userid,usertable.username from usertable left join team on usertable.userid = team.userid group by usertable.userid,usertable.username,usertable.usertype having count(usertable.userid)<2 and usertable.usertype='Tester'";
						psmt=con.prepareStatement(sql);		
						result= psmt.executeQuery();
//						User user=new User();
						while(result.next()) {
							User user=new User();
							System.out.println("useeeerr"+result.getInt("userId"));
							user.setUserId(result.getInt("userId"));
							user.setUserName(result.getString("userName"));
//							user.setUserType(result.getString("userType"));
							
							this.testerList.add(user);
						
					}} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return testerList;
				}
				
				// This Method is used to retrieve a list of all the developers who are not assigned
						// to any project
				public List<User> getDeveloperList(){
					this.developerList.clear();
					try {
						ResultSet result=null;
						String sql="SELECT userTable.userId,userTable.userName,userTable.userType FROM userTable LEFT JOIN team ON userTable.userId = team.userId WHERE team.userId IS NULL and usertable.usertype='Developer'";
						psmt=con.prepareStatement(sql);		
						result= psmt.executeQuery();
						
						while(result.next()) {
							User user=new User();
							user.setUserId(result.getInt("userId"));
							user.setUserName(result.getString("userName"));
			//				user.setUserType(result.getString("userType"));
							
							this.developerList.add(user);
						
					}} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return developerList;
				}
				
				
				public boolean assignToProject(String[] userArr, int projectId) {
					
				      String sqlQuery= "insert into team values(";
						
						boolean flag=false;
						try {
//							psmt= con.prepareStatement();
							Statement stmt = con.createStatement();
							for(int i=0;i<userArr.length;i++){
							stmt.addBatch(sqlQuery+Integer.parseInt(userArr[i])+","+projectId+")");
//							stmt.setInt(Integer.parseInt(userArr[i]));
//							stmt.setInt(projectId;)

							}

							stmt.executeBatch();

//							psmt.setInt(1, userId);
//							psmt.setInt(2, projectId);
//						    psmt.executeUpdate();
						    flag=true;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return flag;
					}
				
				public boolean assignManagerToProject(int managerId, String projectName) {
					
				      String sqlQuery= "insert into team values(?,?)";
					  String projectQuery = "Select * from project where projectName= ?";
						
					  ResultSet result=null;
						boolean flag=false;
						try {
							psmt= con.prepareStatement(projectQuery);
							
							psmt.setString(1, projectName);
						    result=psmt.executeQuery();
							while(result.next()){
							int projectId = result.getInt("projectId");
							
							PreparedStatement psmt1 = con.prepareStatement(sqlQuery);
							
							psmt1.setInt(1, managerId);
							psmt1.setInt(2, projectId);
						    psmt1.executeUpdate();
						    System.out.println("in while of assignToManager");
							}
						    flag=true;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						return flag;
					}
				
				boolean projectUnderManager(int managerId , int noOfProjects){
					boolean flag=true;
					String sql="select count(*) as count from usertable left join team on usertable.userid = team.userid where usertable.userId=? ";
					try {
						PreparedStatement pstmt = con.prepareStatement(sql);
						psmt.setInt(1, managerId);
						ResultSet result = psmt.executeQuery(sql);
						if( result.getInt("count") >= noOfProjects){
							   flag=false;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
//				
					return flag;


					}		
}

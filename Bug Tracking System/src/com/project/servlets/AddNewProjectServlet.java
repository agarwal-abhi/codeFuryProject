/**
 * @author Sarthak Mohanty
 *
 * 
 * Servlet for the add Projects page
 * 
 * This module implements the add project method and also the assignTo method which assigns 
 * a user to a project using doGet to populate the dropdown list and doPost to update the database
 *
 */

package com.project.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.project.daos.ProjectDAOImpl;
import com.project.entity.Project;
import com.project.entity.User;

/**
 * Servlet implementation class addNewProject
 */
@WebServlet("/add")
public class AddNewProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjectDAOImpl dao = new ProjectDAOImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // filters the list of free users according to developers and testers so as to populate the 
    // respective fields in the view page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//            List<User> tester = new ArrayList<User>();
//		    List<User> developer = new ArrayList<User>();
////            List<User> userList = dao.getUserList();
//            System.out.println(userList);
//            for (User eachUser : userList) {
//    			if(eachUser.getUserType().equalsIgnoreCase("tester")) {
//    				tester.add(eachUser);
//    			}else {
//    				developer.add(eachUser);
//    			}
//    		}
//            request.setAttribute("tester", tester);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AddProject.jsp");
            dispatcher.forward(request, response);
//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
		
		
	// makes a call to the database to update the tables accordingly 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Project project = new Project();
		 HttpSession session= request.getSession(true);				//set session for user
		User user =(User)session.getAttribute("activeUser");
		 
//		 project.setProjectId(89111); //to be removed
		 project.setProjectName(request.getParameter("projectName"));
		 project.setProjectDescription(request.getParameter("projectDesc"));
		 project.setStartDate(LocalDate.parse(request.getParameter("startDate")));
		 
		session.setAttribute("project", project);
		System.out.println("project in add new project "+project);
		 try {
			 
			dao.add(project);
//			dao.assignTo(Integer.parseInt(request.getParameter("tester")), 101);
//			dao.assignManagerToProject( user.getUserId(), project.getProjectName());
			dao.assignManagerToProject( user.getUserId(), project.getProjectName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<User> testerList = new ArrayList<User>();
		    List<User> developerList = new ArrayList<User>();
		 testerList = dao.getUserTesterList();
	        developerList=dao.getDeveloperList();
	        System.out.println("do get in add new project"+ testerList);
	        System.out.println(developerList);
	        session.setAttribute("testerList", testerList);
	        session.setAttribute("developerList", developerList);
//		 RequestDispatcher dispatcher = request.getRequestDispatcher("/AddTeamMember"); //teamurl
//         dispatcher.forward(request, response);
	        response.sendRedirect("AddTeamMember");
	}

}

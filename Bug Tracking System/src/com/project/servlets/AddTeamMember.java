package com.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.ProjectDAOImpl;
import com.project.entity.Project;
import com.project.entity.User;

/**
 * @author Sarthak Mohanty
 * @author  Ankit Kashyap
 * 
 * Servlet implementation class AddTeamMember
 */
@WebServlet("/AddTeamMember")
public class AddTeamMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	ProjectDAOImpl dao = new ProjectDAOImpl();
    public AddTeamMember() {
        super();
        // TODO Auto-generated constructor stub
    }
     Project project=new Project();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> testerList = new ArrayList<User>();
	    List<User> developerList = new ArrayList<User>();
	    HttpSession session=request.getSession(true);
	    testerList=(List<User>) session.getAttribute("testerList");
	    developerList=(List<User>) session.getAttribute("developerList");
	    System.out.println("do get invoked");
//	    
//        testerList = dao.getUserTesterList();
//        developerList=dao.getDeveloperList();
        System.out.println("do get"+ testerList);
        System.out.println(developerList);
        request.setAttribute("testerList", testerList);
        request.setAttribute("developerList", developerList);
        System.out.println("trying to get project from session ");
        project=(Project)session.getAttribute("project"); //PROBLEM
        
//        for (User eachUser : userList) {
//			if(eachUser.getUserType().equalsIgnoreCase("tester")) {
//				tester.add(eachUser);
//			}else {
//				developer.add(eachUser);
//			}
//		}
        System.out.println("project is:"+ project);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AddTeam.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do post starts here");
		HttpSession session=request.getSession();
//		System.out.println(session.ge);
		Project project=(Project) (session.getAttribute("project"));
//		System.out.println("project-----"+project.getProjectId());
//		doGet(request, response);
		int tester=Integer.parseInt(request.getParameter("tester"));
		int developer=Integer.parseInt(request.getParameter("developer"));
//		System.out.println("tester"+tester);
		System.out.println("developer"+request.getParameterValues("developer"));
		dao.assignToProject(request.getParameterValues("developer"), project.getProjectId());
		
//		String projectName=dao.findById(projectId).getProjectName(); 
//		dao.assignManagerToProject(managerId, projectName);
//		System.out.println("manager added"+dao.assignManagerToProject(203, "Project2"));;
		System.out.println("tester"+dao.assignTo(tester, project.getProjectId()));
		response.sendRedirect("ProjectServlet");
		
		
		
	}

}

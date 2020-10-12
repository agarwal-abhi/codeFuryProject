package com.project.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.ProjectDAOImpl;
import com.project.daos.UserDAOImpl;
import com.project.entity.Project;
import com.project.ifaces.ProjectDAO;
import com.project.ifaces.UserDAO;

/**
 * Servlet implementation class ProjectServlet
 * @author Gayatri Walve 
 */
@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ProjectDAO projectDao = new ProjectDAOImpl();
		int id = 101;
		List<Project> projList=null;
		try {
			projList  = projectDao.findList(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> namesList = projList.stream().map(prList ->prList.getProjectName()).collect(Collectors.toList());
				
		HttpSession session = request.getSession(true);
		
		if(session != null)
		{
			session.setAttribute("ProjectList",projList);
			session.setAttribute("nameList",namesList);
		}
		System.out.println("let's see details on webpage..."+session.getAttribute("ProjectList"));
		//System.out.println("ProjectList.projectId");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mainpageForManager.jsp");
        dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

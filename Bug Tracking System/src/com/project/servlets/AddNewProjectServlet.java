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
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            List<User> tester = new ArrayList<User>();
		    List<User> developer = new ArrayList<User>();
            List<User> userList = dao.getUserList();
            request.setAttribute("userList", userList);
            for (User eachUser : userList) {
    			if(eachUser.getUserType().equalsIgnoreCase("tester")) {
    				tester.add(eachUser);
    			}else {
    				developer.add(eachUser);
    			}
    		}
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AddProject.jsp");
            dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// makes a call to the database to update the tables accordingly 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Project project = new Project();
		 
		 project.setProjectId(101);
		 project.setProjectName(request.getParameter("projectName"));
		 project.setProjectDescription(request.getParameter("projectDesc"));
		 project.setStartDate(LocalDate.parse(request.getParameter("startDate")));
		 
		 try {
			dao.add(project);
			dao.assignTo(Integer.parseInt(request.getParameter("tester")), 101);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

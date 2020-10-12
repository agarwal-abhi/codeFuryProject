/*
 * @author Raghav Kamra
 * 
 * This servlet is used to show the bugs of a particular project of a manager.
 *  It has the functionalities of assigning bug to a developer, and closing a bug
 */

package com.project.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.BugDAOImpl;
import com.project.entity.Bug;
import com.project.entity.User;

/**
 * Servlet implementation class ShowBugsServlet
 */
@WebServlet("/showBugs/*")
public class ShowBugsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session=null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
	List<Bug> bugList=null;
	List<User> userList=null;
	BugDAOImpl service=null;
	int checkId=0;
	
	User user=(User)session.getAttribute("activeUser");
	String type=user.getUserType();			//Convert it into user.getType()
	int id=user.getUserId();		//Uncomment it
    public ShowBugsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		service=new BugDAOImpl();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session=request.getSession();
		String action = request.getPathInfo();
		System.out.println(action);
		
		
		
		
		if(action == null)
			action="/";
		
			try {
			
			
			switch (action) {
			case "/":
						displayBug(request,response);
				
				break;
			
						
						
						
			case "/marked":
				
				
				markedDeveloper(request, response);
				
				
				break;
				
				
			case "/closed":
				
				closeBug(request,response);
				
				break;

			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public void displayBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		if(type.equalsIgnoreCase("Manager")){
			
		try {
			String projectId=request.getParameter("projectId");
			if(projectId!=""&&projectId!=null) {
				checkId=Integer.parseInt(projectId);
			}
			session.setAttribute("projectID",checkId);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//bugList=service.findListForManager((Integer)session.getAttribute("projectID"));
				bugList=service.findListForManager(checkId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userList=service.findUserForBugs(checkId);
			session.setAttribute("bugList", bugList);
			session.setAttribute("userList",userList);
			request.getRequestDispatcher("/displaybugs.jsp").forward(request, response);
		}
		}
		else {
			
			try {
				bugList=service.findList(id);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	public void markedDeveloper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bugId=Integer.parseInt(request.getParameter("bugId"));
		int userId=Integer.parseInt(request.getParameter("devId"));
		try {
			service.assignDeveloper(userId,bugId);
			displayBug(request, response);
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void closeBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bugId=Integer.parseInt(request.getParameter("bugId"));
		System.out.println(bugId);
		try {
			//User user=(User)session.getAttribute("activeUser");
			service.bugClosed(id,bugId);
			displayBug(request, response);	
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

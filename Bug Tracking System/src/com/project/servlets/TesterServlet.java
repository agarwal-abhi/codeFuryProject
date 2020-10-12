package com.project.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.BugDAOImpl;
import com.project.daos.TeamDAOImpl;
import com.project.entity.Bug;
import com.project.entity.User;
import com.project.ifaces.BugDAO;
import com.project.ifaces.TeamDAO;

/**
 * @author Nehal Goyal
 * Servlet implementation class TesterServlet
 * 
 * 
 * This servlet is used for performing all the functions of a tester such as adding a new bug as well as displaying all the bugs created by him
 */
@WebServlet(urlPatterns = {"/tester/*"})
public class TesterServlet extends HttpServlet {
	
	
	BugDAO bugDAO= null;
	TeamDAO teamDAO=null;
	List<Bug> bugList=null;
	Bug bug;
	HttpSession session;
	User user=null;
	int testerId;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    
    
    
    
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
    	bugDAO= new BugDAOImpl();
    	teamDAO= new TeamDAOImpl();
    	bugList= new ArrayList<Bug>();
    	bug= new Bug();
    	user= new User();
    }







	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		session= request.getSession();
		user = (User)session.getAttribute("activeUser");
		
		testerId= user.getUserId();
		
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("userEmail", user.getUserEmail());
		
		
		
		List<Integer> projectList= teamDAO.findProjectsForUser(testerId); 
		session.setAttribute("projectList",projectList);
		
		
		
		
		
		
		//projectList.forEach(System.out::println);
		
		
		String action = request.getPathInfo();
		
		if(action == null)
			action="/";
				
		try {
			
			
			switch (action) {
			case "/addBug":
						
						addBug(request, response);
						break;
						
			case "/logout":
				
				logout(request, response);
				break;

				
			default:
					displayBug(request, response);
		
				break;
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session.invalidate();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	public void addBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int projectId= Integer.parseInt(request.getParameter("projectId"));   
		String bugTitle= request.getParameter("bugTitle");
		String bugDescription = request.getParameter("bugDescription");
		String severityLevel = request.getParameter("severityLevel");
		int createdBy= 102;
		
		bug = new Bug(projectId, bugTitle, bugDescription, severityLevel, createdBy);
		
		try {
			boolean flag= bugDAO.add(bug);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("tester");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	
	public void displayBug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bugList.clear();
		try {
			bugList=bugDAO.findList(testerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
	
		request.setAttribute("listOfBugs", bugList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/testerPage.jsp");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

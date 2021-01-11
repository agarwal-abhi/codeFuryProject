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
import com.project.entity.User;
import com.project.ifaces.ProjectDAO;
import com.project.ifaces.UserDAO;
/**
 * Servlet implementation class ProjectServlet
 * @author Gayatri Walve 
 */
@WebServlet("/ProjectServlet/*")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	ProjectDAOImpl projectDao = new ProjectDAOImpl();
    public ProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public void showProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	Project project=new Project();
    	int id = Integer.parseInt(request.getParameter("projectId"));
    	System.out.println("in show project method"+id);
    	try {
			project = (Project) projectDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
       HttpSession session = request.getSession(true);
		System.out.println(project);
		if(session != null)
		{
			session.setAttribute("Project",project);
			session.setAttribute("Id", project.getProjectId() );
			session.setAttribute("Name", project.getProjectName());
			session.setAttribute("Description", project.getProjectDescription());
			session.setAttribute("Date", project.getStartDate());
			session.setAttribute("Status", project.getProjectStatus());
			
		}
    	System.out.println("In show Project method:"+session.getAttribute("Project"));
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/showProject.jsp");
        dispatcher.forward(request, response);
    	
    }
    
    public void getProjectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(true);
        User user =  (User)session.getAttribute("activeUser");
		System.out.println(user);
		List<Project> projList=null;
		try {
			projList  = projectDao.findList(user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> namesList = projList.stream().map(prList ->prList.getProjectName()).collect(Collectors.toList());
				
	
		if(session != null)
		{
			session.setAttribute("ProjectList",projList);
		
		}
		System.out.println("let's see details on webpage..."+session.getAttribute("ProjectList"));
		//System.out.println("ProjectList.projectId");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/managerPage.jsp");
        dispatcher.forward(request, response);
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //ProjectDAO projectDao = new ProjectDAOImpl();
		String action = request.getPathInfo();
		System.out.println("path:"+action);
		if(action == null)
			action="/";
		
			try {
			
			
			switch (action) {
			case "/":
				   getProjectList(request,response);
				
				break;
			case "/projectDetails":
				showProject(request,response);
		
		        break;
			}
						
		} catch (Exception e) {
			e.printStackTrace();
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
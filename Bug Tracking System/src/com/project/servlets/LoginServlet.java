package com.project.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.RegisteredUserDAOImpl;
import com.project.entity.RegisteredUser;
import com.project.entity.User;



/**
 * @author Madhura Satao
 * 
 * Servlet implementation class LoginServlet
 * 
 * This module authenticate the user before signing him/her into the system. The email id and password is matched with the database
 * record.
 * On successful verfication it retrieves the details of the user and sets a session for user.
 * Depending on the role, the user is directed on appropriate page.
 *  
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("enteredEmail");
		String password = request.getParameter("enteredPassword");
		
		RegisteredUser user = new RegisteredUser(email,password);
		RegisteredUserDAOImpl registeredUserDao = new RegisteredUserDAOImpl();
		System.out.println("in servlet");
		User verifiedUser = registeredUserDao.verifyUser(user);			//authenticate user 

		if(verifiedUser!=null) {
			System.out.println("User Verified");
			HttpSession session= request.getSession();				//set session for user
			session.setAttribute("activeUser", verifiedUser);
			
			if(verifiedUser.getUserType().equalsIgnoreCase("Manager")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/UserServlet");	//redirect to manager profile
				dispatcher.forward(request, response);
			}
			else if(verifiedUser.getUserType().equalsIgnoreCase("Developer")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/developer");		//redirect to developer profile
				dispatcher.forward(request, response);
			}
			else if(verifiedUser.getUserType().equalsIgnoreCase("Tester")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/tester");			//redirect to tester profile
				dispatcher.forward(request, response);
			}
		}
		else {
			System.out.println("else wala");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");			//redirect to login if authentication failed
			dispatcher.forward(request, response);
		}
		
	}

}

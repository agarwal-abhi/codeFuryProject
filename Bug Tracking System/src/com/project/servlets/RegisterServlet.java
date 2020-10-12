package com.project.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.daos.RegisteredUserDAOImpl;
import com.project.daos.UserDAOImpl;
import com.project.entity.RegisteredUser;



/**
 *  @author Aishwarya Thakur
 * Servlet implementation class RegisterServlet
 * 
 * 
 * This module is used to check the eligibility of the user before registration. It verifies the basic requirements like - 
 * 		- The user details should be already present in the database
 * 		- The user should not have registered before
 * 		- The details entered by the user should match with the database record
 * 
 *  On successful registration it allows the user to create a password and stores it in the database in secured manner.
 *  
 * 
 */

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userEmail = request.getParameter("userEmail");
		String userType = request.getParameter("userType");
		String userPassword = request.getParameter("userPassword");;
		String cnfPassword = request.getParameter("cnfPassword");
		
		UserDAOImpl userDao = new UserDAOImpl();
		
		RegisteredUserDAOImpl registeredUserDao = new RegisteredUserDAOImpl();
				
		if(userPassword.equals(cnfPassword))					// Password matching
		{
			if(userDao.exists(userEmail, userType))				//checks if user exists
			{
				String hashPassword = registeredUserDao.getHashPassword(userPassword);
				
				LocalDateTime defaultTime = LocalDateTime.of(0000, 10, 10, 0, 0, 0);
				
				RegisteredUser user = new RegisteredUser(userEmail, hashPassword, defaultTime);
				
				if(!userDao.registeredUserExists(userEmail))			//checks if user is registered
				{
					if(registeredUserDao.add(user))
					{	//Successful registration
						RequestDispatcher rd= request.getRequestDispatcher("index.html");
						rd.forward(request, response);
					}
					else 
					{
						System.out.println("Error while Registering");
					}
				}
				else
				{
					request.setAttribute("userAlreadyExistsError", "yes");
					request.getRequestDispatcher("Register.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("userDoesNotExistsError", "yes");
				request.getRequestDispatcher("Register.jsp").forward(request, response);
			}
		}
		else
		{
			request.setAttribute("passwordError", "yes");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}
		
	}

}

package com.project.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.UserDAOImpl;
import com.project.entity.User;
import com.project.ifaces.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * @author Gayatri Walve
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDao = new UserDAOImpl();
		//String email = "abc@gmail.com";
		//String sql = "select * from userTable where userEmail = ?";
		HttpSession session = request.getSession(true);
		User entity = (User)session.getAttribute("activeUser");
		User user=null;
		try {
			user  = userDao.findById(entity.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Timestamp timeStamp=null;
		try {
			timeStamp = userDao.getLastLogin(user.getUserEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(timeStamp);
		if(session != null)
		{
			session.setAttribute("userEmail",user.getUserEmail());
			session.setAttribute("userType",user.getUserType());
			session.setAttribute("timeStamp", timeStamp);
		}
		System.out.println("let's see details on webpage..."+user.getUserEmail()+" ,"+user.getUserType()+" ,"+timeStamp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mainpageForManager.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	doGet(request, response);
	}

}

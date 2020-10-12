package com.project.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.project.daos.UserDAOImpl;
import com.project.entity.User;
import com.project.utils.ConnectionUtility;



/**
 * @author Shreyas Subhash Tekawade 
 * @author Ankit Kashyap
 *
 * 
 * Servlet implementation class upload
 * 
 * This module is to import JSON files to server and then extract relevant field to populate our User table in database.
 * The information provided from this JSON file is used to authenticate users i.e only users provided in JSON file can be registered.
 * 
 * File will not be uploaded in these scenario and relevant message will be displayed:
 * 			1.File type is not a JSON.
 * 			2.JSON file is not properly formatted(JSON reader won't be able to parse ,hence excpetion is caught.
 * 			3.File is missing(wrong path and file name mismatch)
 *
 * 
 */

@WebServlet("/upload")
@MultipartConfig()   	//this can be configured for max file size,min file size etc.


public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	    // setting up a directory in project work space named uploads where all uploaded files will be saved(it will be created in .metadata/plugins/...)
		 private static final String UPLOAD_DIR = "uploads";
		 
	 
	 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,JsonSyntaxException, JsonIOException{
		 
		
		//gets the path of project directory
		String applicationPath = request.getServletContext().getRealPath(""); 
		
		//
		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
		
		
		//upload path {your worspace}.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Bug Tracking System\\uploads
		//debugging
		System.out.println("uploadFilePath is =>"+uploadFilePath);
		
		//
		File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        //debugging
        System.out.println(" Upload File Directory => "+fileSaveDir.getAbsolutePath());
        System.out.println("filesavedir is:"+fileSaveDir);
        String fileName = null;
        
        
        File file=null;
        
        //Get all the parts from request and write it to the file on server
        
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            if( file==null ) file = new File(fileName);
            part.write(uploadFilePath + File.separator + file.getName());
        }
        
        //debugging
        System.out.println(" f_Name is =>"+ file.getName());  //BgT_ER.pngj(expected paths)
        System.out.println(" FileName value => "+ fileName);  //C:\Users\ankit\Desktop\BgT_ER.png
        System.out.println(" value of file is:=>" +file);    //C:\Users\ankit\Desktop\BgT_ER.png
        
        
	   	 Gson gson = new Gson();
	   	 String uploadDir=uploadFilePath;
	   	 User[] userList=null;
	   	 boolean rowUpdated=false;
	   	 
	   	try {
	   		
	   		//reads from JSON and maps it as object of class user and stores those object in the list userList.
	   		
	   		 userList = gson.fromJson(new FileReader(uploadDir+"\\"+file), User[].class);
	   	}
	   	
	   	catch (JsonSyntaxException e) {
	   		
	   		request.setAttribute("message", fileName + " Not a json File !!");
	        getServletContext().getRequestDispatcher("/response.jsp").forward(
	                request, response);
		}
	   	
	   	catch (JsonIOException e) {
	   		request.setAttribute("message", fileName + " File is Not Json type!");
	        getServletContext().getRequestDispatcher("/response.jsp").forward(
	                request, response);
		}
	   	
	   	catch (FileNotFoundException e) {
	   		request.setAttribute("message", fileName + " File Not Found !!!");
	        getServletContext().getRequestDispatcher("/response.jsp").forward(
	                request, response);
		}
	   	
	   	UserDAOImpl addService=new UserDAOImpl();
	   	
	   	//To be removed(testing )
	   	
//   		Connection derbyConnection=ConnectionUtility.getDerbyConnection();
   		
   		for (User user : userList) {
   			try {
   				
				rowUpdated=addService.add(user);
				
			} catch (SQLException e) {
				e.getMessage(); // should give some manual message here ??
			}
   			
   			//below codes can be removed
   			
//   			String userName=user.getUserName();
//   			String userType=user.getUserType();
//   			String userEmail=user.getUserEmail();
//   			PreparedStatement pstmt=null;
//   			String sql="insert into UserTable(userName,userEmail,userType) values(?,?,?)";
   			
   			
//   			try {
//				
//   				pstmt=derbyConnection.prepareStatement(sql);
//   				pstmt.setString(1, userName);
//   				pstmt.setString(2, userType);
//   				pstmt.setString(3, userEmail);
//   				rowUpdated=pstmt.executeUpdate();
//   				
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//				
//			} finally {
//				
//				try {
//					
//					pstmt.close();
//					
//				} catch (SQLException e) {
//					
//					e.printStackTrace();
//				}
//			}
   			
   			System.out.println(user); //debugging
//   			//upto here(Remove)
   	}
   		
   		
   		// object is being added in table in for loop.
   		//how to check for each loop the row updated value ??
   		
   		if(rowUpdated) 
   		{
   			
        request.setAttribute("message", fileName + " Hurray !! File uploaded successfully!");
        getServletContext().getRequestDispatcher("/response.jsp").forward(
                request, response);
	   
   		}
   		
		else
		{
			
			request.setAttribute("message", fileName + " File Not uploaded ! Check if file is empty or not properly formatted as JSON");
	        getServletContext().getRequestDispatcher("/response.jsp").forward(
	                request, response);
		}
   		
		}
	 	
	 	
		// utility function to extract file name from response payload
	 	
	 	private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
		
	}





import java.sql.*;
import java.util.List;

import com.project.daos.BugDAOImpl;
import com.project.entity.Bug;

public interface Application {
	
	public static void main(String[] args) {
		Date startDate;
		Date openDate= new Date(2020-12-15);
		
		
		Bug bug= new Bug(111,"Bug hai code mai","Pehle check kar","high",102);
		//Bug bug1= new Bug(111,"Bug hai code mai","Pehle check kar","high",102);
		BugDAOImpl dao= new BugDAOImpl();
		
		try {
			dao.add(bug);
			//dao.add(bug1);
			
			dao.update(900);
			
			List<Bug> bugList=dao.findList(102);
			
			bugList.forEach(System.out::println);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}

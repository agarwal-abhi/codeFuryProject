package com.project.ifaces;

import java.util.List;

public interface TeamDAO {
	
	List<Integer> findProjectsForUser(int id);
	

}

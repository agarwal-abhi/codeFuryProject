package com.project.ifaces;

import java.util.Collection;

import com.project.entity.Bug;

public interface BugDAO extends DAO<Bug> {
	
	Collection<Bug> sortedByDate(); 	//Sort By StartDate using StartDateComparator

}

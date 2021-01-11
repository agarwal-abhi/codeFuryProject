package com.project.ifaces;

import java.sql.SQLException;

import com.project.entity.Project;

public interface ProjectDAO<T> extends DAO<Project> {

	boolean update(int id) throws SQLException;
	
	

}

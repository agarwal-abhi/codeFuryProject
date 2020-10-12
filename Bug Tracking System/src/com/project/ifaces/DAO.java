package com.project.ifaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	boolean add(T t)throws SQLException;
	boolean update(int id)throws SQLException;
	List<T> findAll() throws SQLException;
	List<T> findList(int id) throws SQLException;
	T findById(int id) throws SQLException;
	boolean remove(int id) throws SQLException;
	

}

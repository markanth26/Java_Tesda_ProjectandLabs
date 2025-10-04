/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visitrack.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @param <T>
 */
public interface DAO<T> {
    
    T get(int id) throws SQLException;
    
    List<T> getAll() throws SQLException; //throws SQLException ducking exception 
    
    int save(T t) throws SQLException;
        
    int insert(T t) throws SQLException;
    
    int update(T t) throws SQLException;
    
    int delete(T t) throws SQLException;
}
 /*

Data Transfer Object 
    - object that carries data between processes 
    - Storage and retreival of its own data 
    - simple object that could not contain any logic
*/

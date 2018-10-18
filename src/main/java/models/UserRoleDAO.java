/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Interface for the user role
 * @author Rebecca
 */
public interface UserRoleDAO {
	
    /**
     * Gets the roles from a user in a specific service
     * @param name username
     * @param service id
     * @return user roles
     */
    public String findUserRoleByName(String name, String service);
}

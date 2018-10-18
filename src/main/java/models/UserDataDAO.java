/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * User data interface
 * @author Rebecca
 */
public interface UserDataDAO {

    /**
     * Gets user data by name
     * @param name username
     * @return user data
     */
    public UserData findUserDataByName(String name);
    
    /**
     *  Gets user data by token
     *  @param token user token
     *  @return user data
     */
    public UserData findUserDataByToken(String tk);
 
    /**
     * Inserts the user token in the database
     * @param tk token
     * @param username username
     */
    public void insertUserToken(String tk, String username);
    
    /**
     * Deletes the token from the database
     * @param tk token
     */
    public void destroyToken(String tk);
    
    /**
     * Gets user data by name and password
     * @param name username
     * @passord password
     * @return user data
     */
    public UserData findUserByNameAndPass(String username, String password);
}

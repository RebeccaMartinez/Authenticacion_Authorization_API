/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.security.SecureRandom;
import javax.servlet.http.HttpServletRequest;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que realiza la autenticación de un usuario (login y logout)
 * @author Rebecca
 */
@RestController
public class AuthenticationAPI {

    @Autowired
    private UserDataDAO userDataDAO;

    @Autowired
    private MicroServicesDAO microServicesDAO;

    /**
     * 
     * @param request client request
     * @param name username
     * @param paspassword
     * @param id service id
     * @return 1 + user data if the login is OK. 0 if an error occur
     */
    @RequestMapping(value = "AuthenticationAPI/1.0/login/{name}/{pass}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Message> login(HttpServletRequest request, @PathVariable("name") String name,
			@PathVariable("pass") String pass, @RequestHeader(value="service-id") String id) {
        String microService = microServicesDAO.findMicroServiceID(id);
        Message result = new Message();
        if(microService != null){
            UserData user = userDataDAO.findUserDataByName(name);
            if(user == null){
                result.setMessage("Username doesn't exist");
                result.setCode(0);
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }
            else if(user.getEnabled() == 0){
                result.setMessage("User not enabled");
                result.setCode(0);
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }
            else {
                if(user.getPassword().equals(pass)){
                    String token = generateToken();
                    userDataDAO.insertUserToken(token, user.getUsername());
                    user = userDataDAO.findUserDataByName(user.getUsername());
                    user.setPassword(null);
                    user.setUsername(null);
                    result.setMessage("Login valid");
                    result.setCode(1);
                    result.setObject(user);
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                    result.setMessage("Wrong Password");
                    result.setCode(0);
                    return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
                }
            }
        } 
        else {
            result.setMessage("Servce ID invalid");
            result.setCode(0);
            return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Logout. Tokens are saved in a database. This method deletes the token
     * @param request client request
     * @param token from the user
     * @param id service id
     * @return Logout success of failed
     */
    @RequestMapping(value = "AuthenticationAPI/1.0/logout", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Message> deleteToken(HttpServletRequest request,
			@RequestHeader(value="user-token") String token, @RequestHeader(value="service-id") String id) {
        String microService = microServicesDAO.findMicroServiceID(id);
        Message result = new Message();
        if(microService != null){
            UserData user = userDataDAO.findUserDataByToken(token);
            if(user != null){
                try {
                    userDataDAO.destroyToken(token);
                    result.setMessage("Logout successful");
                    result.setCode(1);
                    return new ResponseEntity<>(result, HttpStatus.OK);
                } catch(Exception e) {
                    result.setMessage("Logout failed");
                    result.setCode(0);
                    return new ResponseEntity<Message>(result, HttpStatus.EXPECTATION_FAILED);
                }
            }
            else {
                result.setMessage("Logout failed, token doesn't exist");
                result.setCode(0);
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }
        }
        else {
            result.setMessage("Servce ID invalid");
            result.setCode(0);
            return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);              
        }
    }

    /**
     * Logout. Tokens are saved in a database. This method deletes the token
     * @param request client request
     * @param token from the user
     * @param id service id
     * @return Logout success of failed
     */
    @RequestMapping(value = "AuthenticationAPI/1.0/logoutP/{token}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Message> deleteTokenP(HttpServletRequest request,
			@RequestHeader(value="user-token") String token, @RequestHeader(value="service-id") String id) {
	    Message result = new Message();
	    UserData user = userDataDAO.findUserDataByToken(token);
	    if(user != null){
	        try {
	            userDataDAO.destroyToken(token);
	            result.setMessage("Logout successful");
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        } catch(Exception e) {
	            result.setMessage("Logout failed");
	            return new ResponseEntity<Message>(result, HttpStatus.EXPECTATION_FAILED);
	        }
	    }
	    else {
	        result.setMessage("Logout failed, token doesn't exist");
	        return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
	    }
	}

    /**
     * Creates a random token
     * @return token
     */
    private String generateToken(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }
}

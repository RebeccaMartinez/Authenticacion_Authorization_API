/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Checksthe users roles and data
 * @author Rebecca
 */

@RestController
public class AuthorizationAPI {

    @Autowired
    private UserDataDAO userDataDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;
    
    @Autowired
    private MicroServicesDAO microServicesDAO;
        
    /**
     * Gets all the data from the User
     * @param request client request
     * @param token from the user
     * @param id service id
     * @return user data
     */
    @RequestMapping(value = "AuthorizationAPI/1.0/getUserData", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Message> getUserData(HttpServletRequest request,
			@RequestHeader(value="user-token") String token, @RequestHeader(value="service-id") String id) {
        String microService = microServicesDAO.findMicroServiceID(id);
        Message result = new Message();
        if(microService != null){ 
            UserData user = userDataDAO.findUserDataByToken(token);
            if(user != null){
                result.setMessage("UserData found");
                result.setObject(user.getUsername());
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.setMessage("UserData not found");
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }
        }
        else {
            result.setMessage("Servce ID invalid");
            return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
        }
    }

    //
    /**
     * Gets the role of an user in a specific service (Each user has a role for each service)
     * @param request client request
     * @param token from the user
     * @param id service id
     * @return user roles for the service
     */
    @RequestMapping(value = "AuthorizationAPI/1.0/getUserRoles", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Message> getUserRoles(HttpServletRequest request,
			@RequestHeader(value="user-token") String token, @RequestHeader(value="service-id") String id) {
            String microService = microServicesDAO.findMicroServiceID(id);
        Message result = new Message();
        if(microService != null) {
            String username = "";
            String roles = "";
            try {
            username = userDataDAO.findUserDataByToken(token).getUsername();
            } catch (Exception e){
                result.setMessage("Token invalid");
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }

            try {
                roles = userRoleDAO.findUserRoleByName(username, microService);
            } catch (Exception e){
                result.setMessage("Service name invalid");
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }

            System.out.println("usernaaame " + username);
            if(roles.isEmpty()){
                result.setMessage("Roles not found");
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }
            else {
                roles = roles.replace(" ", "");
                String[] finalRoles = roles.split(",");
                result.setMessage("Roles found");
                result.setObject(finalRoles);
                return new ResponseEntity<>(result , HttpStatus.OK);
            }
        }
        else {
            result.setMessage("Servce ID invalid");
            return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
        }
    }     
        
    //Returns if a token is valid or not (GET)
    /**
     * Checks if a token is valid
     * @param request client request
     * @param token from the user
     * @param id service id
     * @return token valid or invalid
     */
    @RequestMapping(value = "AuthorizationAPI/1.0/confirmToken", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Message> ConfirmToken(HttpServletRequest request,
			@RequestHeader(value="user-token") String token, @RequestHeader(value="service-id") String id) {
        String microService = microServicesDAO.findMicroServiceID(id);
        Message result = new Message();
        if(microService != null){
            UserData user = userDataDAO.findUserDataByToken(token);
            if(user != null){
                result.setMessage("Token valid");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            else{
                result.setMessage("Invalid Token");
                return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
            }
        }
        else {
            result.setMessage("Servce ID invalid");
            return new ResponseEntity<Message>(result, HttpStatus.NOT_FOUND);
        }
    }
}

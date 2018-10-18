/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class with the user data
 * @author Rebecca
 */
public class UserData {
	
	/** Username*/
    private String username;
    /** Password */
    private String password;
    /** enabled (1-> yes 0 -> no)*/
    private int enabled;
    /** Code */
    private int personCode;
    /** Token */
    private String userToken;
    /** Document type */
    private String documentType;
    /** Document number*/
    private String documentNumber;
    /** Email */
    private String email;
    /** Name */
    private String name;
    /** Surename*/
    private String surename;
    /** Phone number */
    private String phone;
 
    public UserData(String username, String password, int enabled, String userToken, int personCode, String documentType, String documentNumber, String email, String name, String surename, String phone) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userToken = userToken;
        this.personCode = personCode;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.name = name;
        this.surename = surename;
        this.phone = phone;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public int getEnabled(){
        return this.enabled;
    }
    
    public String getUserToken(){
        return this.userToken;
    }
        
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setEnabled(int enabled){
        this.enabled = enabled;
    }
    
    public void setUsertoken(String userToken){
        this.userToken = userToken;
    }

    public String getDocumentType() {
        return documentType;
    }


    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }


    public String getDocumentNumber() {
        return documentNumber;
    }


    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

 
    public String getSurename() {
        return surename;
    }


    public void setSurename(String surename) {
        this.surename = surename;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the personCode
     */
    public int getPersonCode() {
        return personCode;
    }

    /**
     * @param personCode the personCode to set
     */
    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }
}




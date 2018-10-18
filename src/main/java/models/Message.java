/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response messages class
 * @author Rebecca
 */
public class Message {
    private String message;
    private Object object;
    private int code;
    
    public Message(){}
    
    public Message(String message, Object object){
        this.message = message;
        this.object = object;
    }
    
    public Message(String message, Object object, int code){
        this.message = message;
        this.object = object;
        this.code = code;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
     @JsonProperty("data")
    public Object getObject(){
        return this.object;
    }
    
    public void setObject(Object object){
        this.object = object;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public void setCode(int code){
        this.code = code;
    }
    
}

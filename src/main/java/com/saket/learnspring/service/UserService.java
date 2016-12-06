/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saket.learnspring.service;
import org.springframework.stereotype.Service;  
  
import java.util.HashMap;  
import java.util.Map;  
  
@Service("userService")  
public class UserService {  
  
    public Map<String, Object> getUserByUsername(String username) {  
        Map<String, Object> userMap = null;  
        //logic here to get your user from the database  
        if (username.equals("admin") || username.equals("user")) {  
            userMap = new HashMap<String,Object>();  
            userMap.put("username", "admin");  
            userMap.put("password", "saket1012");  
            //if username is admin, role will be admin, else role is user only  
            userMap.put("role", (username.equals("admin")) ? "admin" : "user");  
            //return the usermap  
            return userMap;  
        }  
        //if username is not equal to admin, return null  
        return null;  
    }  
} 
/**
 *
 * @author krishna.kasibhatta
 */


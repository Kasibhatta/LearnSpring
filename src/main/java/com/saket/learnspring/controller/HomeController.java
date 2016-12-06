/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saket.learnspring.controller;

/**
 *
 * @author krishna.kasibhatta
 */

import org.springframework.stereotype.Controller;  
import com.saket.learnspring.custom.CustomUserDetailsService;
import com.saket.learnspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
  
@Controller  
public class HomeController {  
  
    @Autowired 
    CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)  
    public String viewHome() {  
        return "home";  
    }  
  
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)  
    public String viewAdmin() {  
        return "admin";  
    }  
    
    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

            ModelAndView model = new ModelAndView();
            if (error != null) {
                    model.addObject("error", "Invalid username and password!");
            }

            if (logout != null) {
                    model.addObject("msg", "You've been logged out successfully.");
            }
            model.setViewName("login");

            return model;

    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)  
    public String viewRegister() {  
        return "register";  
    }  
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)  
    public String viewRegistrationSuccessful() {  
        return "regsuccess";  
    }  
    
}  
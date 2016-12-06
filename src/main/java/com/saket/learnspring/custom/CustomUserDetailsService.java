/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saket.learnspring.custom;

/**
 *
 * @author krishna.kasibhatta
 */
import com.saket.learnspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.security.core.GrantedAuthority;  
import org.springframework.security.core.authority.SimpleGrantedAuthority;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetails;  
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.core.userdetails.UsernameNotFoundException;  
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  
import org.springframework.security.crypto.password.PasswordEncoder;  
import org.springframework.stereotype.Service;  
  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import org.springframework.beans.factory.annotation.Qualifier;
  
@Service
public class CustomUserDetailsService implements UserDetailsService {  
  
    @Autowired
    @Qualifier("userService")
    private UserService userService;  
  
    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
  
    @Override  
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException { 

        Map<String, Object> userMap = userService.getUserByUsername(s);  
        
        //check if this user with this username exist, if not, throw an exception  
        // and stop the login process  
        if (userMap == null) {  
            throw new UsernameNotFoundException("User details not found with this username: " + s);  
        }  
  
        String username = (String) userMap.get("username");  
        String password = (String) userMap.get("password");  
        String role = (String) userMap.get("role");  
        List authList = getAuthorities(role);  
  
        //get the encoded password  
        String encodedPassword = passwordEncoder.encode(password);  
        User user = new User(username, encodedPassword, authList);  
  
        return user;  
    }  
  
    private List getAuthorities(String role) {  
        List authList = new ArrayList();  
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));  
  
        //you can also add different roles here  
        //for example, the user is also an admin of the site, then you can add ROLE_ADMIN  
        //so that he can view pages that are ROLE_ADMIN specific  
        if (role != null && role.trim().length() > 0) {  
            if (role.equals("admin")) {  
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
            }  
        }  
  
        return authList;  
    }  
} 

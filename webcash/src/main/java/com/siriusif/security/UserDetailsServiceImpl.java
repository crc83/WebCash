package com.siriusif.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

 private HashMap<String, org.springframework.security.core.userdetails.User> users 
		 	= new HashMap<String, org.springframework.security.core.userdetails.User>();
 
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	org.springframework.security.core.userdetails.User user = users.get(username);
  
  if (user == null) {
   throw new UsernameNotFoundException("UserAccount for name \""
     + username + "\" not found.");
  }
  
  return user;
 }

 @PostConstruct
 public void init() {
  
  // sample roles  
  Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
  adminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
  
  Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
  userAuthorities.add(new SimpleGrantedAuthority("ROLE_REGISTERED"));
  
  boolean enabled = true;
  boolean accountNonExpired = true;
  boolean credentialsNonExpired = true;
  boolean accountNonLocked = true;
  
  // sample users with roles set
  users.put("admin", new org.springframework.security.core.userdetails.User("admin", "admin", enabled, accountNonExpired,
    credentialsNonExpired, accountNonLocked, adminAuthorities));
  
  users.put("user", new org.springframework.security.core.userdetails.User("user", "user", enabled, accountNonExpired,
    credentialsNonExpired, accountNonLocked, userAuthorities));
 }
}
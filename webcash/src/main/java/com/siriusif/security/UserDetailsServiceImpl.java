package com.siriusif.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.siriusif.model.User;
import com.siriusif.service.model.UserDao;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	//TODO SB : Cover with tests
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.findByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserAccount for name \""
					+ username + "\" not found.");
		}

		org.springframework.security.core.userdetails.User userSpring = convertToUserDetails(user);
		return userSpring;
	}

	//TODO SB : Cover with tests
	private org.springframework.security.core.userdetails.User convertToUserDetails(
			User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		// role wrapper
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));

		
		return new org.springframework.security.core.userdetails.User(user.getLogin(),
				user.getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked,
				authorities);
	}
}
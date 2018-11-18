package com.cvut.corsys.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cvut.corsys.dao.UserDao;
import com.cvut.corsys.entity.User;

@Service("corsysUserDetailsService")
public class CorsysUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> usr = userDao.findByUsername(username);
		if (usr.isEmpty()) {
			return null;
		}
		return new CorsysUserDetails(usr.get(0));
	}

}

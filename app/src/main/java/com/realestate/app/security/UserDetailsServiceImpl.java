package com.realestate.app.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.impl.UserRepositoryImpl;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepositoryImpl userRepository;

	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity userEntity = userRepository.getUserByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User: "+username+" not found");
		}
		RoleEntity role = userEntity.getRole();
		
		logger.info("getting role name {}",role);
		
		return UserPrincipal.build(userEntity, role.getRoleName());
	}
}

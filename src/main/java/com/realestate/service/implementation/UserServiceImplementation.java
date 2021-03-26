package com.realestate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.dto.UserDtoForCreate;
import com.realestate.exception.CustomUserException;
import com.realestate.model.UserEntity;
import com.realestate.repository.UserRepository;
import com.realestate.service.UserService;
@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
//	public UserEntity addUser(UserDtoForCreate user) {
//		if (user != null) {
//			if (user.getFirstName() != null) {
//				if (user.getLastName() != null) {
//					if (
//						throw new CustomUserException("Could not register user");
//					}
//				} else {
//					System.out.println("Last name is mandatory");
//					throw new CustomUserException("Last name is mandatory");
//
//				}
//			} else {
//				System.out.println("First name is mandatory");
//				throw new CustomUserException("First name is mandatory");
//			}
//		}
//		return null;
//	}
	
	


}

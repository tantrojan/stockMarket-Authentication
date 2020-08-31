package org.wells.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wells.models.AddUserRequest;
import org.wells.models.User;
import org.wells.models.enums.UserTypes;
import org.wells.util.UserRepository;

import java.util.ArrayList;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if(user==null) {
        	throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
    	
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
    }
    
    
	public User save(AddUserRequest userRequest) {
		User newUser = new User();
		newUser.setUsername(userRequest.getUsername());
		newUser.setPassword(userRequest.getPassword());
		newUser.setFirstName(userRequest.getFirstName());
		newUser.setLastName(userRequest.getLastName());
		newUser.setMobile(userRequest.getMobile());
		newUser.setConfirmed(true);
		newUser.setEmail(userRequest.getEmail());
		newUser.setUserType(UserTypes.CONSUMER);
		
		return userRepository.save(newUser);
	}
    
    
}

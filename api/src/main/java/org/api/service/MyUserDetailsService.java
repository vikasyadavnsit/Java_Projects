package org.api.service;

import java.util.Optional;

import org.api.repository.UserRepository;
import org.api.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<UserWrapper> user = userRepository.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("User Doesn't Exist"));
		return user.map(MyUserDetails::new).get();
	}

}

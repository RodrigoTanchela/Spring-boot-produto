package com.example.springboot.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.springboot.repository.UsuarioRepository;

@Service
public class UsuarioServices implements UserDetailsService {
	
	private Logger logger = Logger.getLogger(UsuarioServices.class.getName());
	
	@Autowired
	private UsuarioRepository repository;
	
	public UsuarioServices(UsuarioRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name " + username + "!");
	    var usuario = repository.findByUsername(username);
	    if (usuario != null) {
	        return usuario;
	    } else {
	        throw new UsernameNotFoundException("Username " + username + " not found!");
	    }
	}

}

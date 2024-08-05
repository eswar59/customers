package com.piggy.customers.service;

import com.piggy.customers.repository.CustomerRepository;
import com.piggy.customers.model.UserSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;

    public JpaUserDetailsService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByUsername(username)
                .map(UserSecurity::new)
                .orElseThrow(()-> new UsernameNotFoundException("The customer name not found is:" + username));
    }
}

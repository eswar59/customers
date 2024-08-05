package com.piggy.customers.controller;

import com.piggy.customers.exception.NotAuthorizedException;
import com.piggy.customers.exception.UserIdNotExistsException;
import com.piggy.customers.model.User;
import com.piggy.customers.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public CustomerController( CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        this.customerRepository=customerRepository;
        this.passwordEncoder=passwordEncoder;
    }

    //create a customer
    @PostMapping("public/customers")
    public User createCustomer(@Valid @RequestBody User user){
        user.setRoles("ROLE_CUSTOMER");
        String plainPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(plainPassword));
        customerRepository.save(user);
        return user;
    }

    // returning user with his orders only authorized
    @GetMapping("/customers/{id}")
    public User returnUser(@PathVariable int id, Principal principal){
        Optional <User> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new UserIdNotExistsException("No customer exists with id "+ id);
        }
        // user id exists now see if the one requesting is user himself
        String loggedInUsername = principal.getName();
        if(loggedInUsername.equals(customer.get().getUsername())){
            return customer.get();
        }else{
            throw new NotAuthorizedException("You are not authorized to request details of customer with id:"+ id);
        }
    }

}

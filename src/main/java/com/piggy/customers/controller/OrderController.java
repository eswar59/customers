package com.piggy.customers.controller;

import com.piggy.customers.exception.UserIdNotExistsException;
import com.piggy.customers.kafka.ProducerService;
import com.piggy.customers.model.Order;
import com.piggy.customers.model.User;
import com.piggy.customers.repository.CustomerRepository;
import com.piggy.customers.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import com.piggy.customers.exception.NotAuthorizedException;
import shared.CustomerOrder;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ProducerService producerService;

    public OrderController(CustomerRepository customerRepository, OrderRepository orderRepository, ProducerService producerService){
        this.orderRepository=orderRepository;
        this.customerRepository=customerRepository;
        this.producerService=producerService;
    }

    @PostMapping("/customers/{id}/orders")
    public Order creatingOrder(@Valid @RequestBody Order order, @PathVariable int id, Principal principal){
        Optional<User> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new UserIdNotExistsException("No customer exists with id "+ id);
        }
        String loggedInUsername = principal.getName();
        if(loggedInUsername.equals(customer.get().getUsername())){
            order.setUser(customer.get());
            order.setStatus("ORDERED");
            orderRepository.save(order);
            // create customerOrder and send to producer to send to topic
            // we don't want to send user details to restaurant
            CustomerOrder cOrder= new CustomerOrder(order.getId(), order.getStatus(), order.getAddress(), order.getMobile(), order.getItemId());
            producerService.sendMessage(cOrder);
            return order;
        }
        else{
            //return null;
            throw new NotAuthorizedException("You are not authorized to make order with customer id:"+ id);
        }
    }

    @GetMapping("/customers/{id}/orders")
    public List<Order> retriveOrdersOfCustomer(@PathVariable int id, Principal principal){
        Optional<User> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new UserIdNotExistsException("No customer exists with id "+ id);
        }
        String loggedInUsername = principal.getName();
        if(loggedInUsername.equals(customer.get().getUsername())){
            return customer.get().getOrders();
        }
        else{
            //return null;
            throw new NotAuthorizedException("You are not authorized to get orders list with customer id:"+ id);
        }
    }
}

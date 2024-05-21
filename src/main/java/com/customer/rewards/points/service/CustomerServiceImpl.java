package com.customer.rewards.points.service;

import com.customer.rewards.points.entity.Customer;
import com.customer.rewards.points.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findCustomerByCustomerId(String customerId) {
        Customer customerData = customerRepository.findByCustomerId(customerId);
        return customerData;
    }

    @Override
    public void createCustomer(Customer customer) {
        if(null != customerRepository.findByCustomerId(customer.getCustomerId())) {
            throw new RuntimeException("Customer Already Exists !!!");
        } else {
            customerRepository.save(customer);
        }
    }

    @Override
    public void deleteCustomerById(String customerId) {
        Optional<Customer> customerData = customerRepository.findById(customerId);
        if(!customerData.isPresent()) {
           throw new RuntimeException("Customer not found!!!");
        } else {
            customerRepository.deleteByCustomerId(customerId);
        }
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}

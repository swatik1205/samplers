package com.customer.rewards.points.service;

import com.customer.rewards.points.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer findCustomerByCustomerId(String customerId);
    void createCustomer(Customer customer);
    void deleteCustomerById(String customerId);
    Page<Customer> getAllCustomers(Pageable pageable);
}

package com.customer.rewards.points.controller;

import com.customer.rewards.points.entity.Customer;
import com.customer.rewards.points.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/customers")
    public ResponseEntity<Map<String, Object>> findAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Customer> customers = new ArrayList<>();
            Pageable pageable = PageRequest.of(page, size);

            Page<Customer> customerPages = customerService.getAllCustomers(pageable);
            customers = customerPages.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("customers", customers);
            response.put("currentPage", customerPages.getNumber());
            response.put("totalItems", customerPages.getTotalElements());
            response.put("totalPages", customerPages.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {
        try {
            return new ResponseEntity<>(customerService.findCustomerByCustomerId(customerId),HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<Customer> saveCustomerData(@RequestBody Customer customer){
        try {
            customerService.createCustomer(customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("id") String customerId)
    {
        try {
            customerService.deleteCustomerById(customerId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

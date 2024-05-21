package com.customer.rewards.points.controller;

import com.customer.rewards.points.entity.Customer;
import com.customer.rewards.points.model.Rewards;
import com.customer.rewards.points.service.CustomerService;
import com.customer.rewards.points.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") String customerId){
        Customer customer = customerService.findCustomerByCustomerId(customerId);
        if(customer == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }
}

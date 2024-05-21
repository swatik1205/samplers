package com.customer.rewards.points.repository;

import com.customer.rewards.points.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends MongoRepository<Customer,String> {

    Page<Customer> findAll(Pageable pageable);
    Customer findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);
}

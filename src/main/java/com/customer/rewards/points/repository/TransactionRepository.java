package com.customer.rewards.points.repository;

import com.customer.rewards.points.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends MongoRepository<Transaction,String> {

   // List<Transaction> findAllByCustomerId(String customerId);
}

package com.customer.rewards.points.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "customer")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private List<Transaction> transactions;

    public Customer() {

    }
}

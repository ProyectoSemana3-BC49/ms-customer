package com.nttdatabc.mscustomer.repository;

import com.nttdatabc.mscustomer.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
  Mono<Customer> findByIdentifier(String identifier);
}

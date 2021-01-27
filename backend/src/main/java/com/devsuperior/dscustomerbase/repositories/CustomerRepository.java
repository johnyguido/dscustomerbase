package com.devsuperior.dscustomerbase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscustomerbase.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

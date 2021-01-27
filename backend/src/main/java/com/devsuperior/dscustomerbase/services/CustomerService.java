package com.devsuperior.dscustomerbase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscustomerbase.entities.Customer;
import com.devsuperior.dscustomerbase.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public List<Customer> findAll(){
		return repository.findAll();
	}

}

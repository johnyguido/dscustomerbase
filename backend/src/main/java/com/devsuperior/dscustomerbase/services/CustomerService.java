package com.devsuperior.dscustomerbase.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscustomerbase.dto.CustomerDTO;
import com.devsuperior.dscustomerbase.entities.Customer;
import com.devsuperior.dscustomerbase.exceptions.CustomerNotFoundException;
import com.devsuperior.dscustomerbase.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	@Transactional(readOnly = true)
	public List<CustomerDTO> findAll(){
		List<Customer> list = repository.findAll();
		return list.stream().map(x -> new CustomerDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CustomerDTO findById(Long id) {
		Optional<Customer> obj = repository.findById(id);
		Customer entity = obj.orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
		return new CustomerDTO(entity);
	}

}

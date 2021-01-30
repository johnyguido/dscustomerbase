package com.devsuperior.dscustomerbase.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public List<CustomerDTO> findAll() {
		List<Customer> list = repository.findAll();
		return list.stream().map(x -> new CustomerDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CustomerDTO findById(Long id) {
		Optional<Customer> obj = repository.findById(id);
		Customer entity = obj.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		return new CustomerDTO(entity);
	}

	@Transactional(readOnly = true)
	public CustomerDTO insert(CustomerDTO dto) {
		Customer entity = new Customer();
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity = repository.save(entity);

		return new CustomerDTO(entity);

	}

	@Transactional
	public CustomerDTO update(Long id, CustomerDTO dto) {

		try {
			Customer entity = repository.getOne(id);

			entity.setName(dto.getName());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
			entity = repository.save(entity);

			return new CustomerDTO(entity);
		}

		catch (EntityNotFoundException e) {
			throw new CustomerNotFoundException("Id not found " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CustomerNotFoundException("Id not found " + id);

		}

	}

}

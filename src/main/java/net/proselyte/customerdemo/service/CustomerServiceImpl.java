package net.proselyte.customerdemo.service;

import lombok.extern.slf4j.Slf4j;
import net.proselyte.customerdemo.model.Customer;
import net.proselyte.customerdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        log.info("Get by id " + id);
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
        log.info("Save our customer with id " + customer.getId());
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete customer with id " + id);
        customerRepository.delete(id);
    }

    @Override
    public List<Customer> getAll() {
        log.info("Get all customer");
        return customerRepository.findAll();
    }
}

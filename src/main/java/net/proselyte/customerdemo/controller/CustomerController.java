package net.proselyte.customerdemo.controller;

import net.proselyte.customerdemo.model.Customer;
import net.proselyte.customerdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId) {
        if(customerId == null) return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);

        Customer customer = customerService.getById(customerId);

        if(customer == null) return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
        HttpHeaders headers = new HttpHeaders();

        if (customer == null) return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);

        customerService.save(customer);
        return new ResponseEntity<Customer>(customer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer, UriComponentsBuilder builder) {
       if (customer == null) return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);

       customerService.save(customer);
       return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getById(id);

        if (customer == null) return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

        customerService.delete(id);

        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> list = customerService.getAll();
        if (list.isEmpty()) return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }
}

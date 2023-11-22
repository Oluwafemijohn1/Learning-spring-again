package com.ephemzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StarterMain.class, args);
    }

    /**
     * \d :-
     *
     * INSERT INTO customer(id, name, email, age)
     * VALUES (nextval('customer_id_sequence'), 'Alex', 'alex@gmail.com', 23);
     * */
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}

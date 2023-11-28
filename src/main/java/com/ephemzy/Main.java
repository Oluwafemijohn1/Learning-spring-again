package com.ephemzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * <p>
     * INSERT INTO customer(id, name, email, age)
     * VALUES (nextval('customer_id_sequence'), 'Alex', 'alex@gmail.com', 23);
     */
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRecord request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody  NewCustomerRecord request) {

        return customerRepository.findById(id)
                .map(customer -> {
                    if (request.name() != null && !request.name().isEmpty()) customer.setName(request.name());
                    if (request.email() != null && !request.email().isEmpty()) customer.setEmail(request.email());
                    if (request.age() != null) customer.setAge(request.age());
                    customerRepository.save(customer);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private record NewCustomerRecord(
            String name,
            String email,
            Integer age
    ) {

    }
}

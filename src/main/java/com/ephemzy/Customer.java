package com.ephemzy;

import jakarta.persistence.*;

import java.util.Objects;


/**
 * We configure our model with JPA:
 * Jakarta Persistence (JPA; formerly Java Persistence API) is a Jakarta EE
 * application programming interface specification that describes the management
 * of relational data in enterprise Java applications.
 * <p>
 * Docker:
 * docker compose ps
 * <p>
 * Also:
 * docker logs postgress -f
 * <p>
 * to create bd with postgres:
 * -> docker ps
 * -> docker exec -it postgress bash  :- this allows us to execute shell command within the container
 * <p>
 * -> psql -U amigoscode  :-psql is for progress sql, -U is for User, and the amigoscode the username from the docker yml file.
 * <p>
 * -> \l   :-to list the databases
 * <p>
 * -> CREATE DATABASE customer;  :- a sql code to create database in the bash
 * <p>
 * -> \l    :- to see the database list again
 * <p>
 * -> ctrl D   :- to come out of the shell to come out of psql
 * <p>
 * -> ctrl D    :- to come out of the container shell again.
 * <p>
 * -> \c customer     :- to connect to customer db
 * <p>
 * -> \dt    :- to show list of relations
 * <p>
 * -> \d      :- to show list of relations
 * <p>
 * */
@Entity

public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public Customer(Integer id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}

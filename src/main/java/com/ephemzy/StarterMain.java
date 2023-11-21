package com.ephemzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


/**
 * @SpringBootApplication is a wrapper for encapsulating @Configuration, @EnableAutoConfiguration, @ComponentScan
 * @Configuration: This annotation provides one or more Spring Bean definitions.
 * @Bean methods and may be processed by the Spring container to generate
 * bean definitions and service requests for those beans at runtime.
 * @EnableAutoConfiguration: This annotation tells Spring Boot to start adding beans
 * based on classpath settings, other beans, and various property settings.
 * Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it
 * automatically when it sees spring-webmvc on the classpath. This flags the application
 * as a web application and activates key behaviors such as setting up a DispatcherServlet.
 * @ComponentScan: This annotation tells Spring to look for other components, configurations,
 * and services in the com/example package, letting it find the controllers.
 */
@SpringBootApplication
@RestController
public class StarterMain {


    /**
     * What is tomcat?
     * <p>
     * Apache Tomcat is a free and open-source implementation of the Jakarta Servlet,
     * Jakarta Expression Language, and WebSocket technologies.
     * <p>
     * It provides a "pure Java" HTTP web server environment in which Java code can also run.
     * Thus, it is a Java web application server, although not a full JEE application server.
     * <p>
     * Developer: Apache Software Foundation
     *
     * <p>
     * Other web servers include:
     * Jetty -> From Eclipse foundation
     * <p>
     * An embedded web server is can be Tomcat, Jetty e.t.c
     */
    public static void main(String[] args) {
        SpringApplication.run(StarterMain.class, args);
    }


    @GetMapping("/language-greeting")
    public LanguageGreetResponse greetWithLanguage() {
        return new LanguageGreetResponse(
                "Hello",
                List.of(
                        "Java",
                        "JavaScript",
                        "Kotlin"
                ),
                new Person("Femi", 28, 30_000_000)
        );
    }

    record Person(
            String name,
            int age,
            double savings
    ) {

    }

    record LanguageGreetResponse(
            String greet,
            List<String> favProgrammingLanguage,
            Person person
    ) {
    }


    @GetMapping("/greet")
    public GreetResponse greet() {
        return new GreetResponse("Hello");
    }

    //    record GreetResponse(String greet){ }

    /**
     * If we are using a class like this, there must be get for the fields,
     * otherwise the API will not return the right response.
     */
    static class GreetResponse {
        String greet;

        GreetResponse(String greet) {
            this.greet = greet;
        }

        public String getGreet() {
            return greet;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GreetResponse that = (GreetResponse) o;
            return Objects.equals(greet, that.greet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(greet);
        }

        @Override
        public String toString() {
            return "GreetResponse{" +
                    "greet='" + greet + '\'' +
                    '}';
        }
    }

}

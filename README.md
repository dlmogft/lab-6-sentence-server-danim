# lab-6-sentence-server-danim

This is a Spring Cloud Eureka client project that is registered and discovered in the **lab-4-eureka-server-danim**<br>
This project acts as a **load-balancer** of **lab-5-word-server-danim** repo executions, and also uses **Spring Cloud Open Feign** to call the other services using FeignClient interfaces. It also includes a Circuit Breaker feature to respond opening it and respond to failures

# Starting this client repository

The steps to used it are:
- Start the **lab-3-server-danim** repo, which loads the configuration files from the **spring-cloud-server-config-danim** Git repository
- Start the **lab-4-eureka-server-danim** repo and check it's started correctly in http://localhost:8010
- Start the different executions of **lab-5-word-server-danim** ([See README from lab-5-word-server-danim](https://github.com/dlmogft/lab-5-word-server-danim/blob/main/README.md))
- Start the class annotated with @SpringBootApplication
- Check that the sentences are shown in the URL http://localhost:8020/sentence

# Dependencies

Spring Web, Config Server, Eureka Discovery Client, Spring Boot Actuator, Spring Boot DevTools, Cloud LoadBalancer Open Feign, Resilience4J (Circuit Breaker)

# Tips

- The project starting class is annotated with **@SpringBootApplication** and also with @EnableDiscoveryClient to indicate that acts as an Eureka Client, and with @EnableFeignClients to indicate that the controllers will use Feign Clients to call the different executions of **lab-5-word-server-danim**
- The **application.yml** config file specifies the URI of the Config Server repo **lab-3-server-danim** that loads the configuration files from the **spring-cloud-server-config-danim** Git repository
- There are several interfaces (SubjectClient, VerbClient, etc) that are annotated with **@FeignClient** including the service name ("SUBJECT", "VERB", etc) to indicate that are used to call the executions of **lab-5-word-server-danim**, through their getWord method
- The implementation of the SentenceService (annotated with @Service) builds the sentence calling the WordService, that calls the Feing clients implementing calls to services using a CircuitBreaker created with the CircuitBreakerFactory. The SentenceController (annotated with @Controller) calls the service to return the sentence

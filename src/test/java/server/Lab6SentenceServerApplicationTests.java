package server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Lab6SentenceServerApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    // Test to ensure the application context loads successfully
    @Test
    public void Application_Context_ShouldLoadSuccessfully() {
        assertNotNull(restTemplate, "RestTemplate bean should be initialized and not null.");
    }

    // Test to ensure the RestTemplate bean is LoadBalanced
    @Test
    public void RestTemplate_ShouldBeLoadBalanced() {
        assertTrue(restTemplate.getInterceptors().stream()
                        .anyMatch(interceptor -> interceptor.getClass().getSimpleName().contains("LoadBalancer")),
                "RestTemplate should have a LoadBalancer interceptor.");
    }
}

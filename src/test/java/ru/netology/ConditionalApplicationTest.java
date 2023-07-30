package ru.netology;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
	
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConditionalApplicationTest {
	@Autowired
	TestRestTemplate restTemplate;

	@Container
	private final GenericContainer<?> myAppDev = new GenericContainer<>("devapp:1.0")
			.withExposedPorts(8080);
	@Container
	private final GenericContainer<?> myAppProd = new GenericContainer<>("prodapp:1.0")
			.withExposedPorts(8081);

	/*
	 * @BeforeAll
	 * public static void setUp() {
	 * myAppDev.start();
	 * myAppProd.start();
	 * }
	 */

	@Test
	void devContextLoads() {
		final String expected = "Current profile is dev";
		final String actual;
		ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/profile", String.class);
		actual = forEntityDev.getBody();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void prodContextLoads() {
		final String expected = "Current profile is production";
		final String actual;
		ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + myAppProd.getMappedPort(8081) + "/profile", String.class);
		actual = forEntityProd.getBody();
		Assertions.assertEquals(expected, actual);
	}
}

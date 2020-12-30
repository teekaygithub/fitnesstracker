package com.tomkato.fitnesstracker.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getHome() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/home").toString(), String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
    
    @Test
    public void getWelcome() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/").toString(), String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void getAbout() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/about").toString(), String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void getRecord() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/record").toString(), String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
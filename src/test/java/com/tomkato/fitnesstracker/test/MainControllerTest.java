package com.tomkato.fitnesstracker.test;

import com.tomkato.fitnesstracker.exercise.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
    
    private Logger logger = LoggerFactory.getLogger(MainControllerTest.class);
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
    
    @MockBean
    private ExerciseRepository mockRepository;
    
    @Test
    public void getExerciseById() throws Exception {
        Exercise exercise = new Exercise(1, "running", 15, "2020-12-25");
        when(mockRepository.findExerciseById(1)).thenReturn(exercise);
        String expected = "{id:1,name:\"running\",duration:15,date:\"2020-12-25\"}";
        
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/log/1").toString(), String.class
        );
        
        logger.debug("Expected: {}", expected);
        logger.debug("Actual: {}", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
        
        verify(mockRepository, times(1)).findExerciseById(1);
    }
	
	@Test
	public void getAllExercises() throws Exception {
		Exercise log1 = new Exercise(1,"running",10,"2020-12-25");
		Exercise log2 = new Exercise(2,"biking",20,"2020-01-01");
		List<Exercise> logs = new ArrayList<>();
		logs.add(log1);
		logs.add(log2);
		when(mockRepository.findAll()).thenReturn(logs);
		
		String expected =  "[{id:1,name:\"running\",duration:10,date:\"2020-12-25\"},"+
		"{id:2,name:\"biking\",duration:20,date:\"2020-01-01\"}]";
		
		ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/log").toString(), String.class
		);
		
		logger.info("Expected: {}", expected);
		logger.info("Actual: {}", response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		verify(mockRepository, times(1)).findAll();
	}
	
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
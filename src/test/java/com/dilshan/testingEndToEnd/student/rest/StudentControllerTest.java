package com.dilshan.testingEndToEnd.student.rest;

import com.dilshan.testingEndToEnd.student.StudentRepository;
import com.dilshan.testingEndToEnd.student.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

/*
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "classpath:application.properties")
@DataJpaTest
@Sql({"classpath:init-data.sql"})*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@Sql({"classpath:init-data.sql"})
@Slf4j
class StudentControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String URL = "/api/v1/students";


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStudents() {
        log.info("This is test: {}", this.studentRepository.findAll());
    }

    @Test
    public void testAddEmployee() throws Exception {

        String requestUrl = "http://localhost:" + port + URL;
        String response = restTemplate.getForObject(requestUrl, String.class);
        log.info("This is entity: {}", response);
        // execute
        // ResponseEntity<String> responseEntity = restTemplate.getForObject(requestUrl, String.class);
        //log.info("This is entity: {}", responseEntity);

    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudent() {
    }
}
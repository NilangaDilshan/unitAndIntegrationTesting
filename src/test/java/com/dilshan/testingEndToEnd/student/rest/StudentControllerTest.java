package com.dilshan.testingEndToEnd.student.rest;

import com.dilshan.testingEndToEnd.student.StudentRepository;
import com.dilshan.testingEndToEnd.student.entities.Gender;
import com.dilshan.testingEndToEnd.student.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
//@Sql({"classpath:init-data.sql"})
@Slf4j
class StudentControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private static String URL;

    @BeforeEach
    void setUp() {
        URL = "http://localhost:" + port + "/api/v1/students";
    }

    @AfterEach
    void tearDown() {
        this.studentRepository.deleteAll();
    }

    @Test
    @Sql({"classpath:init-data.sql"})
    void getAllStudents() {
        log.info("This is test: {}", this.studentRepository.findAll());
    }

    @Test
    public void testAddEmployee() throws Exception {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                }
        );
        log.info("This is entity: {}", response);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addStudent() {
        String email = "dimmu@gmail.com";
        ResponseEntity<Void> response = restTemplate.postForEntity(URL, Student.builder().name("dimmu").email(email)
                .gender(Gender.MALE).build(), Void.class);
        log.info("This is the post request response: {}", response);
    }

    @Test
    @Sql({"classpath:init-data.sql"})
    void deleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange(
                URL + "/1",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Void>() {
                }
        );
        log.info("This is the post request response: {}", response);
    }
}
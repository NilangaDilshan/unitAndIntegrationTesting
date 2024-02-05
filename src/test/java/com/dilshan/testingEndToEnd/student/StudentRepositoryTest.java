package com.dilshan.testingEndToEnd.student;

import com.dilshan.testingEndToEnd.student.entities.Gender;
import com.dilshan.testingEndToEnd.student.entities.Student;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }


    @Test
    void itShouldCheckIfStudentEmailExists() {
        //given
        String email = "araya@gmail.com";
        Student student = Student.builder().name("Slayer").email(email).gender(Gender.MALE).build();
        this.studentRepository.save(student);
        //when
        boolean expected = this.studentRepository.selectExistsEmail(email);
        //then
        AssertionsForClassTypes.assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        //given
        String email = "araya@gmail.com";
        //when
        boolean expected = this.studentRepository.selectExistsEmail(email);
        //then
        AssertionsForClassTypes.assertThat(expected).isFalse();
    }

}
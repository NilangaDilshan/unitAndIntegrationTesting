package com.dilshan.testingEndToEnd.student;

import com.dilshan.testingEndToEnd.student.entities.Gender;
import com.dilshan.testingEndToEnd.student.entities.Student;
import com.dilshan.testingEndToEnd.student.exception.BadRequestException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    //This is replaced since we used @ExtendWith(MockitoExtension.class) annotation at the top
    /*private AutoCloseable autoCloseable;*/
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        //This is replaced since we used @ExtendWith(MockitoExtension.class) annotation at the top
        /*autoCloseable = MockitoAnnotations.openMocks(this);*/
        this.studentService = new StudentService(studentRepository);
    }

    //This is replaced since we used @ExtendWith(MockitoExtension.class) annotation at the top
    /*@AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
    }*/

    @Test
    void canGetAllStudents() {
        //when
        studentService.getAllStudents();
        //then
        Mockito.verify(studentRepository).findAll();
    }

    @Test
        //@Disabled
    void canAddStudent() {
        //given
        String email = "araya@gmail.com";
        Student student = Student.builder().name("Slayer").email(email).gender(Gender.MALE).build();

        //When
        studentService.addStudent(student);

        //Then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        AssertionsForClassTypes.assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
        //@Disabled
    void canNotAddStudent() {
        //given
        String email = "araya@gmail.com";
        Student student = Student.builder().name("Slayer").email(email).gender(Gender.MALE).build();

        //When
        //Then
        BDDMockito.given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);

        assertThatThrownBy(() -> this.studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");
        Mockito.verify(studentRepository, Mockito.never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudent() {
        //when
        Long studentId = 1l;
        studentService.deleteStudent(studentId);
        //then
        Mockito.verify(studentRepository).deleteById(studentId);
    }
}
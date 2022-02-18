package jpa.enrolment.repository;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentRepositoryTest {
    @Autowired StudentRepository studentRepository;
    @Autowired DepartmentRepository departmentRepository;

    @Test
    void save() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);

        studentRepository.save(student);
        Student findStudent = studentRepository.findOne(student.getId()).get();

        assertThat(student).isEqualTo(findStudent);
    }

    @Test
    void findAll() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student1 = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);
        Student student2 = Student.createStudent("12345-12345","김현민","lo@naverㅇ","20180111","12344",department,3);

        studentRepository.save(student1);
        studentRepository.save(student2);
        List<Student> result = studentRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(student1,student2);

    }

    @Test
    void login성공() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);
        studentRepository.save(student);

        Long loginStudentId = studentRepository.login(student.getLoginId(), student.getLoginPw());

        assertThat(student.getId()).isEqualTo(loginStudentId);
    }

    @Test
    void login실패() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);
        studentRepository.save(student);

        Assertions.assertThrows(EmptyResultDataAccessException.class,()->studentRepository.login(student.getLoginId(), "2sf"));

    }

    @Test
    void delete() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);
        studentRepository.save(student);

        Long deleteId = studentRepository.delete(student.getId());
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,()->studentRepository.findOne(deleteId).get());


    }
}
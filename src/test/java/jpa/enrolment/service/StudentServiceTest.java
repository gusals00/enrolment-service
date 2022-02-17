package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.dto.StudentUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceTest {

    @Autowired StudentService studentService;
    @Autowired DepartmentRepository departmentRepository;

    @Test
    void update() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);
        studentService.saveStudent(student);

        StudentUpdateDTO studentUpdateDTO = new StudentUpdateDTO(student.getId(),student.getSsn(),student.getName(),student.getEmail(),"1234","01234",department,student.getStudentLevel());
        studentService.update(studentUpdateDTO);

        Student updatedStudent = studentService.findOne(student.getId());
        Assertions.assertThat(updatedStudent.getLoginId()).isEqualTo("1234");//로그인아이디(학번) 변경 확인
        Assertions.assertThat(updatedStudent.getLoginPw()).isEqualTo("01234");//비번 변경 확인

    }
}


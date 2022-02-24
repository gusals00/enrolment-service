package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.domain.person.dto.update.StudentUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        studentService.joinStudent(student);

        StudentUpdateParam studentUpdateParam = new StudentUpdateParam(student);
        studentUpdateParam.setLoginId("1234");
        studentUpdateParam.setLoginPw("01234");

        studentService.update(student.getId(),studentUpdateParam);

        Student updatedStudent = studentService.findOne(student.getId()).get();
        Assertions.assertThat(updatedStudent.getLoginId()).isEqualTo("1234");//로그인아이디(학번) 변경 확인
        Assertions.assertThat(updatedStudent.getLoginPw()).isEqualTo("01234");//비번 변경 확인
    }

    @Test
    void 회원가입성공(){
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);

        Long savedId = studentService.joinStudent(student);
        Student findStudent = studentService.findOne(savedId).get();

        Assertions.assertThat(savedId).isEqualTo(findStudent.getId());

    }


    @Test
    void 회원가입실패(){
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Student student1 = Student.createStudent("1234-1234","성호창","lo@naver","20180584","1234",department,3);
        Student student2 = Student.createStudent("1234-123422","성호창2","lo@naver222","20180584","123434",department,2);

        studentService.joinStudent(student1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> studentService.joinStudent(student2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 가입된 student가 있습니다.");

    }
}


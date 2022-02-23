package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.LectureRepository;
import jpa.enrolment.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OpenLectureServiceTest {

    @Autowired
    OpenLectureService openLectureService;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ProfessorService professorService;

    @Test
    @Rollback(value = false)
    void createOpenLecture() {
        Lecture lecture = Lecture.builder()
                .code("CS00101")
                .credit(2)
                .name("자바")
                .level(3)
                .build();

        lectureRepository.save(lecture);

        Department department = Department.createDepartment(1,"컴소공");
        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorService.joinProfessor(professor);

        openLectureService.createOpenLecture(lecture.getCode(), professor.getId(), 30, 2, 10);
    }
}
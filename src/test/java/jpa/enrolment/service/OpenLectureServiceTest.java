package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.LectureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OpenLectureServiceTest {

    @Autowired
    OpenLectureService openLectureService;

    @Autowired
    LectureService lectureService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ProfessorService professorService;

    @Autowired
    LectureRepository lectureRepository;

    @Test
    void createOpenLecture() {
        Lecture lecture = createLecture("CS00101","자바",3,2);
        lectureService.saveLecture(lecture);
        Department department = Department.createDepartment(1,"컴소공");
        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorService.joinProfessor(professor);

        Lecture findLecture = lectureRepository.findOne(lecture.getId()).get();

        openLectureService.createOpenLecture(findLecture.getCode(), professor.getId(), 30, 2, 10);
    }

    @Test
    void createOpenLectureFail(){
        Lecture lecture = createLecture("CS00101","자바",3,2);
        lectureService.saveLecture(lecture);
        Department department = Department.createDepartment(1,"컴소공");
        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorService.joinProfessor(professor);

        Lecture findLecture = lectureRepository.findOne(lecture.getId()).get();
        openLectureService.createOpenLecture(findLecture.getCode(), professor.getId(), 30, 2, 10);

        IllegalStateException e1 = assertThrows(IllegalStateException.class, () -> openLectureService.createOpenLecture("cs", professor.getId(), 30, 2, 10));
        org.assertj.core.api.Assertions.assertThat(e1.getMessage()).isEqualTo("찾으려는 lecture가 없음");

        IllegalStateException e2 = assertThrows(IllegalStateException.class, () -> openLectureService.createOpenLecture(findLecture.getCode(),1000L, 30, 2, 10));
        org.assertj.core.api.Assertions.assertThat(e2.getMessage()).isEqualTo("찾으려는 professor가 없음");

        IllegalStateException e3 = assertThrows(IllegalStateException.class, () -> openLectureService.createOpenLecture(findLecture.getCode(),professor.getId(), 30, 2, 10));
        org.assertj.core.api.Assertions.assertThat(e3.getMessage()).isEqualTo("존재하는 분반이 있습니다");

    }


    private Lecture createLecture(String code,String name, int level,int credit) {
        return Lecture.builder()
                .code(code)
                .credit(credit)
                .name(name)
                .level(level)
                .build();
    }


}
package jpa.enrolment.repository;

import jpa.enrolment.domain.OpenLecture;
import jpa.enrolment.domain.person.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OpenLectureRepositoryTest {

    @Autowired OpenLectureRepository openLectureRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    void save() {
        OpenLecture openLecture = OpenLecture.builder()
                .seperatedNumber(2)
                .maxStudentNumber(20)
                .curStudentNumber(13)
                .build();

        openLectureRepository.save(openLecture);

        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", null, "010", "421");
        em.persist(professor);
        openLectureRepository.addProfessor(openLecture.getId(), professor);

    }

}
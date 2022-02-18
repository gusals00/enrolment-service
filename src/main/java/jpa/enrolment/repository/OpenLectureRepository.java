package jpa.enrolment.repository;

import jpa.enrolment.domain.OpenLecture;
import jpa.enrolment.domain.person.Person;
import jpa.enrolment.domain.person.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpenLectureRepository {

    private final EntityManager em;

    public void save(OpenLecture openLecture) {
        em.persist(openLecture);
    }

    public void addProfessor(Long openLectureId, Professor professor) {
        OpenLecture openLecture = em.find(OpenLecture.class, openLectureId);
        openLecture.addProfessor(professor);
    }


}

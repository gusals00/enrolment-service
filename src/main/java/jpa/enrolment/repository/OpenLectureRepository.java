package jpa.enrolment.repository;

import jpa.enrolment.domain.lecture.OpenLecture;
import jpa.enrolment.domain.person.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

package jpa.enrolment.repository;

import jpa.enrolment.domain.Lecture;
import jpa.enrolment.domain.person.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepository {

    private final EntityManager em;

    public void save(Lecture lecture) {
        em.persist(lecture);
    }

    public List<Lecture> findAll() {
        return em.createQuery("select l from Lecture l", Lecture.class)
                .getResultList();
    }

    public Lecture findOne(Long id) {
        return em.find(Lecture.class, id);
    }

    public Long delete(Long id) {
        Lecture deleteLecture = em.find(Lecture.class, id);
        em.remove(deleteLecture);

        return id;
    }
}

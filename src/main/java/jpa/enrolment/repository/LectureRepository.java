package jpa.enrolment.repository;

import jpa.enrolment.domain.lecture.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    public Optional<Lecture> findOne(Long id) {
        return Optional.ofNullable(em.find(Lecture.class, id));
    }

    public Optional<Lecture> findByCode(String code) {
        return em.createQuery("select l from Lecture l where l.code = :code")
                .setParameter("code", code)
                .getResultList().stream().findFirst();
    }

    public Long delete(Long id) {
        Lecture deleteLecture = em.find(Lecture.class, id);
        em.remove(deleteLecture);

        return id;
    }
}

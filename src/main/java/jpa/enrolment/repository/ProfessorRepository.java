package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProfessorRepository {

    private final EntityManager em;

    public void save(Professor professor) {
        em.persist(professor);
    }

    public List<Professor> findAll() {
        return em.createQuery("select p from Professor p", Professor.class)
                .getResultList();
    }

    public Professor findOne(Long id) {
        return em.find(Professor.class, id);
    }

    public Long login(String id, String pw) {
        return em.createQuery("select p.id from Professor p where p.loginId = :id and p.loginPw = :pw", Professor.class)
                .setParameter("id", id)
                .setParameter("pw", pw)
                .getSingleResult()
                .getId();
    }

    public void update() {

    }
}

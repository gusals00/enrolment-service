package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    public Optional<Professor> findOne(Long id) {
        return Optional.ofNullable(em.find(Professor.class, id));
    }

    public Long login(String loginId, String loginPw) {
        return em.createQuery("select p from Professor p where p.loginId = :id and p.loginPw = :pw", Professor.class)
                .setParameter("id", loginId)
                .setParameter("pw", loginPw)
                .getSingleResult()
                .getId();
    }

    public Long delete(Long id) {
        Professor deleteProfessor = em.find(Professor.class, id);
        em.remove(deleteProfessor);

        return id;
    }

    public List<Professor> findByLoginId(String loginId) {
        return em.createQuery("select  p from Professor p where p.loginId =:loginId ", Professor.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }
}

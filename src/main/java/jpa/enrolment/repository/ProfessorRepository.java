package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.web.interceptor.SessionAuth;
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

    public SessionAuth login(String loginId, String loginPw) {
        List<Professor> result = em.createQuery("select p from Professor p where p.loginId = :id and p.loginPw = :pw", Professor.class)
                .setParameter("id", loginId)
                .setParameter("pw", loginPw)
                .getResultList();

        if(result.isEmpty())
            return null;
        else
            return new SessionAuth(result.get(0).getId(), "professor");
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

    public List<Professor> findByName(String name) {
        return em.createQuery("select p from Professor p where p.name like :name", Professor.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public Professor findOneWithDepartment(Long id) {
        return em.createQuery("select p from Professor p join fetch p.department where p.id=:id", Professor.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager em;

    public void save(Admin admin) {
        em.persist(admin);
    }

    public Long login(String id, String pw) {
        return em.createQuery("select a.id from Admin a where a.loginId = :id and a.loginPw = :pw", Admin.class)
                .setParameter("id", id)
                .setParameter("pw", pw)
                .getSingleResult()
                .getId();
    }

    public Admin findOne(Long id) {
        return em.find(Admin.class, id);
    }
}

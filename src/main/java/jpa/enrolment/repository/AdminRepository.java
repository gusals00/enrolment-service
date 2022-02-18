package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager em;

    public void save(Admin admin) {
        em.persist(admin);
    }

    public Long login(String loginId, String loginPw) {
        return em.createQuery("select a from Admin a where a.loginId = :id and a.loginPw = :pw", Admin.class)
                .setParameter("id", loginId)
                .setParameter("pw", loginPw)
                .getSingleResult()
                .getId();
    }

    public Optional<Admin> findOne(Long id) {
        return Optional.ofNullable(em.find(Admin.class, id));
    }

    public Long delete(Long id) {
        Admin deleteAdmin = em.find(Admin.class, id);
        em.remove(deleteAdmin);
        return id;
    }

    public List<Admin> findByLoginId(String loginId){
        return em.createQuery("select  a from Admin a where a.loginId =:loginId ", Admin.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }
}

package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final EntityManager em;

    public void save(Student student) {
        em.persist(student);
    }

    public List<Student> findAll() {
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    public Student findOne(Long id) {
        return em.find(Student.class, id);
    }

    public Long login(String id, String pw) {
        return em.createQuery("select s.id from Student s where s.loginId = :id and s.loginPw = :pw", Student.class)
                .setParameter("id", id)
                .setParameter("pw", pw)
                .getSingleResult()
                .getId();
    }

    public void update() {

    }
}

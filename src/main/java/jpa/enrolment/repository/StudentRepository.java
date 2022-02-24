package jpa.enrolment.repository;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.web.interceptor.SessionAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    public Optional<Student> findOne(Long id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    public SessionAuth login(String loginId, String loginPw) {
        List<Student> result = em.createQuery("select s from Student s where s.loginId = :id and s.loginPw = :pw", Student.class)
                .setParameter("id", loginId)
                .setParameter("pw", loginPw)
                .getResultList();

        if(result.isEmpty())
            return null;
        else
            return new SessionAuth(result.get(0).getId(), "student");

    }

    public Long delete(Long id) {
        Student deleteStudent = em.find(Student.class, id);
        em.remove(deleteStudent);
        return id;
    }

    public List<Student> findByLoginId(String loginId) {
        return em.createQuery("select  s from Student s where s.loginId =:loginId ", Student.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    public List<Student> findByName(String name) {
        return em.createQuery("select s from Student s where s.name like :name", Student.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public Student findOneWithDepartment(Long id) {
        return em.createQuery("select s from Student s join fetch s.department where s.id=:id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}

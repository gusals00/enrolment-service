package jpa.enrolment.repository;

import jpa.enrolment.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public void save(Department department){
        em.persist(department);
    }
}

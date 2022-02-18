package jpa.enrolment.repository;

import jpa.enrolment.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public void save(Department department){
        em.persist(department);
    }

    public List<Department> findByName(String name){
        return em.createQuery("select d from Department d where d.name =: departmentName",Department.class)
                .setParameter("departmentName",name)
                .getResultList();
    }

    public Optional<Department> findById(Long id){
        return Optional.ofNullable(em.find(Department.class,id));
    }

    public List<Department> findAll(){
        return em.createQuery("select d from Department d",Department.class).getResultList();
    }

    public List<Department> findByNumber(int number){
        return em.createQuery("select d from Department d where d.number =: number",Department.class)
                .setParameter("number",number)
                .getResultList();
    }

}

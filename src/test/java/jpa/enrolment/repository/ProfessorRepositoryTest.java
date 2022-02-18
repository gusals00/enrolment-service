package jpa.enrolment.repository;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Professor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProfessorRepositoryTest {

    @Autowired ProfessorRepository professorRepository;
    @Autowired DepartmentRepository departmentRepository;

    Department department;
    Professor professor;

    @BeforeEach
    public void beforeEach() {
        department = Department.createDepartment(1,"컴소공");
        professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");

        departmentRepository.save(department);
        professorRepository.save(professor);
    }

    @Test
    void save() {
        assertThat(professorRepository.findOne(professor.getId())).isEqualTo(professor);
    }

    @Test
    void login() {
        assertThat(professorRepository.login(professor.getLoginId(), professor.getLoginPw())).isEqualTo(professor.getId());
    }

    @Test
    void delete() {
        Long deleteId = professorRepository.delete(professor.getId());
        assertThat(professorRepository.findOne(deleteId)).isNull();
    }
}
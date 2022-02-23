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

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    Department department;
    Professor professor;

    @BeforeEach
    public void beforeEach() {
        department = Department.createDepartment(1, "컴소공");
        professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");

        departmentRepository.save(department);
        professorRepository.save(professor);
    }

    @Test
    void save() {
        assertThat(professorRepository.findOne(professor.getId()).get()).isEqualTo(professor);
    }

    @Test
    void login() {
        assertThat(professorRepository.login(professor.getLoginId(), professor.getLoginPw()).getPersonId()).isEqualTo(professor.getId());
    }

    @Test
    void delete() {
        Long deleteId = professorRepository.delete(professor.getId());
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> professorRepository.findOne(deleteId).get());
    }

    @Test
    void findByName() {
        department = Department.createDepartment(1, "컴소공");
        professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorRepository.save(professor);

        Department department2 = Department.createDepartment(2, "컴공");
        Professor professor2 = Professor.createProfessor("1232-456", "김수", "aaa@a", "id3", "pw1", department, "0102", "3421");
        departmentRepository.save(department2);
        professorRepository.save(professor2);

        List<Professor> professors = professorRepository.findByName(professor.getName());
        Assertions.assertThat(professors).contains(professor,professor2);

    }


}
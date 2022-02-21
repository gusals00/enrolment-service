package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.ProfessorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProfessorServiceTest {

    @Autowired ProfessorService professorService;
    @Autowired DepartmentRepository departmentRepository;
    @Autowired EntityManager em;
    @Autowired
    ProfessorRepository professorRepository;
    @Test
    void update() {
        Department department = Department.createDepartment(1,"컴소공");
        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorService.joinProfessor(professor);

        Professor changeProfessor = Professor.createProfessor(professor.getSsn(), "김222", professor.getEmail(), professor.getLoginId(), professor.getLoginPw(), department, professor.getPhoneNumber(), professor.getLabNumber());
        ProfessorUpdateParam professorUpdateParam = new ProfessorUpdateParam(changeProfessor);

        professorService.update(professor.getId(),professorUpdateParam);
        Optional<Professor> findProfessor = professorRepository.findOne(professor.getId());

        assertThat(findProfessor.get().getName()).isEqualTo(changeProfessor.getName());
    }

    @Test
    void 회원가입성공(){
        Department department = Department.createDepartment(1,"컴소공");
        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);

        Long savedId = professorService.joinProfessor(professor);
        Professor findProfessor = professorService.findOne(savedId).get();

        assertThat(savedId).isEqualTo(findProfessor.getId());
    }

    @Test
    void 회원가입실패(){
        Department department = Department.createDepartment(1,"컴소공");
        Professor professor1 = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorService.joinProfessor(professor1);

        Professor professor2 = Professor.createProfessor("123-4536", "김3", "aa33a@a", "id", "pw33", department, "01033", "42133");

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> professorService.joinProfessor(professor2));
        assertThat(e.getMessage()).isEqualTo("이미 가입된 professor가 있습니다.");
    }
}
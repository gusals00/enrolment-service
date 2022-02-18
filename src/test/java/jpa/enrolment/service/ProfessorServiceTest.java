package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProfessorServiceTest {

    @Autowired ProfessorService professorService;
    @Autowired DepartmentRepository departmentRepository;

    @Test
    void update() {
        Department department = Department.createDepartment(1,"컴소공");
        Professor professor = Professor.createProfessor("123-456", "김", "aaa@a", "id", "pw", department, "010", "421");
        departmentRepository.save(department);
        professorService.saveProfessor(professor);

        ProfessorUpdateDTO professorUpdateDTO = new ProfessorUpdateDTO(professor.getId(), professor.getSsn(), professor.getName(), "update", professor.getLoginId(), professor.getLoginPw(), professor.getPhoneNumber(), professor.getLabNumber(), department);
        professorService.update(professorUpdateDTO);

        assertThat(professor.getEmail()).isEqualTo("update");
    }
}
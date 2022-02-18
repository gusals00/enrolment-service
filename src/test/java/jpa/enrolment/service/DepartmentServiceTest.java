package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class DepartmentServiceTest {

    @Autowired DepartmentService departmentService;
    @Autowired DepartmentRepository departmentRepository;
    @Test
    void 학과저장성공(){
        Department department=Department.createDepartment(100,"컴소공");
        departmentService.saveDepartment(department);

        Optional<Department> savedDepartment = departmentRepository.findById(department.getId());

        assertThat(department).isEqualTo(savedDepartment.get());

    }

    @Test
    void 학과저장실패(){
        Department department1=Department.createDepartment(100,"컴소공");
        Department department2=Department.createDepartment(100,"컴공");
        Department department3=Department.createDepartment(200,"컴소공");

        departmentService.saveDepartment(department1);

        IllegalStateException exception1 = Assertions.assertThrows(IllegalStateException.class, () -> departmentService.saveDepartment(department2));
        assertThat(exception1.getMessage()).isEqualTo("같은 번호의 학과가 이미 등록되어 있습니다");

        IllegalStateException exception2 = Assertions.assertThrows(IllegalStateException.class, () -> departmentService.saveDepartment(department3));
        assertThat(exception2.getMessage()).isEqualTo("같은 이름의 학과가 이미 등록되어 있습니다");
    }
}

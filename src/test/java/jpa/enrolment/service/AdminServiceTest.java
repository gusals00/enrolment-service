package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.dto.AdminUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AdminServiceTest {
    @Autowired AdminService adminService;
    @Autowired DepartmentRepository departmentRepository;

    @Test
    void update() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminService.saveAdmin(admin);

        AdminUpdateDTO adminUpdateDTO = new AdminUpdateDTO(admin.getId(),admin.getSsn(),admin.getName(),admin.getEmail(),"*****","*",department);
        adminService.update(adminUpdateDTO);

        Admin updatedAdmin = adminService.findOne(admin.getId());
        Assertions.assertThat(updatedAdmin.getLoginId()).isEqualTo("*****");//로그인아이디(학번) 변경 확인
        Assertions.assertThat(updatedAdmin.getLoginPw()).isEqualTo("*");//비번 변경 확인
    }
}


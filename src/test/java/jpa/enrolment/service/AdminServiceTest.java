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
        adminService.joinAdmin(admin);

        AdminUpdateDTO adminUpdateDTO = new AdminUpdateDTO(admin.getSsn(),admin.getName(),admin.getEmail(),"*****","*",department);
        adminService.update(admin.getId(),adminUpdateDTO);

        Admin updatedAdmin = adminService.findOne(admin.getId()).get();
        Assertions.assertThat(updatedAdmin.getLoginId()).isEqualTo("*****");//로그인아이디(학번) 변경 확인
        Assertions.assertThat(updatedAdmin.getLoginPw()).isEqualTo("*");//비번 변경 확인
    }

    @Test
    void 회원가입성공(){
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);

        Long savedId = adminService.joinAdmin(admin);
        Admin findAdmin = adminService.findOne(savedId).get();

        Assertions.assertThat(savedId).isEqualTo(findAdmin.getId());
    }

    @Test
    void 회원가입실패(){
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin1 = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        Admin admin2 = Admin.createAdmin("12342-12341","admin2","lo@nave33r","555","33111",department);

        adminService.joinAdmin(admin1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> adminService.joinAdmin(admin2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 가입된 admin이 있습니다.");

    }
}


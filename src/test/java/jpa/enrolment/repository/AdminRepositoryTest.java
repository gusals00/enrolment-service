package jpa.enrolment.repository;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class AdminRepositoryTest {
    @Autowired AdminRepository adminRepository;
    @Autowired DepartmentRepository departmentRepository;

    @Test
    void save() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);

        adminRepository.save(admin);
        Admin findAdmin = adminRepository.findOne(admin.getId());

        assertThat(admin).isEqualTo(findAdmin);
    }

    @Test
    void login성공() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminRepository.save(admin);

        Long loginAdminId = adminRepository.login(admin.getLoginId(), admin.getLoginPw());
        assertThat(admin.getId()).isEqualTo(loginAdminId);
    }

    @Test
    void login실패() {

        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminRepository.save(admin);

        Assertions.assertThrows(EmptyResultDataAccessException.class,()->adminRepository.login(admin.getLoginId(), "2555"));

    }

    @Test
    @Rollback(value = false)
    void delete() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminRepository.save(admin);

        adminRepository.delete(admin.getId());
        Admin result = adminRepository.findOne(admin.getId());

        assertThat(result).isEqualTo(null); // em.find()는 원하는 entity 없는 경우 null을 반환
    }
}
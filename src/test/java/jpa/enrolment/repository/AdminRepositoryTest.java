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
import java.util.NoSuchElementException;

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
        Admin findAdmin = adminRepository.findOne(admin.getId()).get();

        assertThat(admin).isEqualTo(findAdmin);
    }

    @Test
    void login성공() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminRepository.save(admin);

        Long savedId = adminRepository.login(admin.getLoginId(), admin.getLoginPw()).getPersonId();
        assertThat(admin.getId()).isEqualTo(savedId);
    }

    @Test
    void login실패() {

        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminRepository.save(admin);

        org.assertj.core.api.Assertions.assertThat(adminRepository.login(admin.getLoginId(), "2555")).isNull();

    }

    @Test
    void delete() {
        Department department = Department.createDepartment(1,"컴소공");
        departmentRepository.save(department);
        Admin admin = Admin.createAdmin("1234-1234","admin1","lo@naver","555","111",department);
        adminRepository.save(admin);

        Long deleteId = adminRepository.delete(admin.getId());

        Assertions.assertThrows(NoSuchElementException.class,()->adminRepository.findOne(deleteId).get());
    }
}
package jpa.enrolment;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.service.AdminService;
import jpa.enrolment.service.DepartmentService;
import jpa.enrolment.service.ProfessorService;
import jpa.enrolment.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.professorInitDb();
    }
    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{

        private final ProfessorService professorService;
        private final DepartmentService departmentService;
        private final AdminService adminService;
        private final StudentService studentService;

        public void professorInitDb(){
            Department department = Department.createDepartment(1,"토목");
            Professor professor = Professor.createProfessor("123-456", "강현민", "aaa@a", "555", "555", department, "010", "4231");
            departmentService.saveDepartment(department);
            professorService.joinProfessor(professor);

            Department department2 = Department.createDepartment(2,"화공");
            Professor professor2 = Professor.createProfessor("123-23456", "강대현", "a22aa@a", "222", "222", department, "010", "4241");
            departmentService.saveDepartment(department2);
            professorService.joinProfessor(professor2);

            Admin admin = Admin.createAdmin("123-456", "강현민", "aaa@a", "111", "111", null);
            adminService.joinAdmin(admin);

            Student student = Student.createStudent("123-456", "강현민", "aaa@a", "333", "333", department, 3);
            studentService.joinStudent(student);
        }

    }
}



package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.domain.person.dto.update.StudentUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long update(Long id,StudentUpdateParam studentUpdateParam){
        Optional<Student> student = studentRepository.findOne(id);
        Optional<Department> department = departmentRepository.findById(studentUpdateParam.getDepartmentId());

        StudentUpdateDTO studentUpdateDTO = new StudentUpdateDTO(studentUpdateParam.getSsn(), studentUpdateParam.getName(), studentUpdateParam.getEmail(), studentUpdateParam.getLoginId(), studentUpdateParam.getLoginPw(), department.get(), studentUpdateParam.getStudentLevel());
        student.get().change(studentUpdateDTO);
        log.info("update student id={},loginId={},name={}",student.get().getId(),student.get().getLoginId(),student.get().getName());
        return student.get().getId();
    }

    public List<Student> findStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student> findOne(Long studentId) {
        return studentRepository.findOne(studentId);
    }

    @Transactional
    public Long joinStudent(Student student){
        validateLoginId(student.getLoginId());

        studentRepository.save(student);
        log.info("join student id={},loginId={},name={}",student.getId(),student.getLoginId(),student.getName());
        return student.getId();
    }

    public void validateLoginId(String loginId){
        if (!studentRepository.findByLoginId(loginId).isEmpty()) {
            throw new IllegalStateException("이미 가입된 id가 있습니다.");
        }
    }

    @Transactional
    public Long deleteStudent(Long studentId) {
        return studentRepository.delete(studentId);
    }

}

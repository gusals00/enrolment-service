package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public void saveDepartment(Department department){
        validateDepartmentName(department.getName());
        validateDepartmentNumber(department.getNumber());

        departmentRepository.save(department);
    }

    public void validateDepartmentName(String name) {
        if (!departmentRepository.findByName(name).isEmpty()) {
            throw new IllegalStateException("같은 이름의 학과가 이미 등록되어 있습니다");
        }
    }
    public void validateDepartmentNumber(int number){
        if (!departmentRepository.findByNumber(number).isEmpty()) {
            throw new IllegalStateException("같은 번호의 학과가 이미 등록되어 있습니다");
        }
    }
}

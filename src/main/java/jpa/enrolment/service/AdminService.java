package jpa.enrolment.service;

import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.AdminUpdateDTO;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public void update(AdminUpdateDTO adminUpdateDTO) {
        Admin admin = adminRepository.findOne(adminUpdateDTO.getId());
        admin.change(adminUpdateDTO);
    }
    @Transactional
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin findOne(Long adminId) {
        return adminRepository.findOne(adminId);
    }

    public Long loginAdmin(String id, String pw) {
        return adminRepository.login(id, pw);
    }

}

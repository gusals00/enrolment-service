package jpa.enrolment.service;

import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.AdminUpdateDTO;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.AdminRepository;
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
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public Long update(Long id, AdminUpdateDTO adminUpdateDTO) {
        Optional<Admin> admin = adminRepository.findOne(id);
        admin.get().change(adminUpdateDTO);
        log.info("update admin id={},loginId={},name={}",admin.get().getId(),admin.get().getLoginId(),admin.get().getName());
        return admin.get().getId();
    }

    public Optional<Admin> findOne(Long adminId) {
        return adminRepository.findOne(adminId);
    }

    public Long loginAdmin(String id, String pw) {
        return adminRepository.login(id, pw);
    }

    @Transactional
    public Long joinAdmin(Admin admin){
        validateLoginId(admin.getLoginId());
        adminRepository.save(admin);
        log.info("join admin id={},loginId={},name={}",admin.getId(),admin.getLoginId(),admin.getName());
        return admin.getId();
    }

    public void validateLoginId(String loginId){
        if(!adminRepository.findByLoginId(loginId).isEmpty()){
            throw new IllegalStateException("이미 가입된 admin이 있습니다.");
        }

    }
}

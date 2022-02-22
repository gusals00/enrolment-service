package jpa.enrolment.web.controller.admin;

import jpa.enrolment.web.controller.DepartmentChooseDTO;
import jpa.enrolment.web.controller.ProfessorDetail;
import jpa.enrolment.service.ProfessorUpdateParam;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.ProfessorRepository;
import jpa.enrolment.service.ProfessorService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/professor")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final ProfessorService professorService;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String professorList(@ModelAttribute ProfessorSearch professorSearch, Model model){
        log.info("admin.ProfessorController.ProfessorList");
        List<ProfessorQueryDTO> result;
        if(StringUtils.hasText(professorSearch.getName())){ //검색 조건 있는 경우
            result = getProfessorSimpleDTOS(professorSearch.getName());

        } else { //모든 교수 조회
            result =  getProfessorSimpleDTOS();
        }

        log.info("/admin/professor professorList size={}",result.size());
        model.addAttribute("professors",result);
        return "/admin/professor/professorList";
    }


    @GetMapping("/{professorId}")
    public String professor(@PathVariable("professorId")Long id, Model model){
        ProfessorDetail professorDetail = new ProfessorDetail(professorRepository.findOneWithDepartment(id));
        model.addAttribute("professor",professorDetail);
        return "/admin/professor/professor";
    }

    @GetMapping("/{professorId}/edit")
    public String editForm(@PathVariable("professorId")Long id,Model model){
        Professor findProfessor = professorRepository.findOne(id).get();

        List<DepartmentChooseDTO> departmentChooseList = changeDepartmentChooseDTOS();

        model.addAttribute("updateForm",new ProfessorUpdateParam(findProfessor));
        model.addAttribute("departmentList",departmentChooseList);

        return "/admin/professor/professorUpdateForm";
    }


    @PostMapping("/{professorId}/edit")
    public String edit(@PathVariable("professorId")Long id, @ModelAttribute("updateForm") ProfessorUpdateParam updateForm){

        professorService.update(id,updateForm);

        return "redirect:/admin/professor/{professorId}";
    }

    @PostMapping("/{professorId}/delete")
    public String delete(@PathVariable("professorId")Long id){
        professorService.delete(id);
        return "redirect:/admin/professor";
    }

    public List<DepartmentChooseDTO> changeDepartmentChooseDTOS() {
        List<DepartmentChooseDTO> departmentChooseList = departmentRepository.findAll().stream()
                .map(d -> new DepartmentChooseDTO(d))
                .collect(Collectors.toList());
        return departmentChooseList;
    }


    public List<ProfessorQueryDTO> getProfessorSimpleDTOS(String name) {
        return professorRepository.findByName(name)
                .stream()
                .map(p -> new ProfessorQueryDTO(p))
                .collect(Collectors.toList());
    }

    public List<ProfessorQueryDTO> getProfessorSimpleDTOS() {
        return professorRepository.findAll()
                .stream()
                .map(p -> new ProfessorQueryDTO(p))
                .collect(Collectors.toList());
    }

    @Getter @Setter
    static class ProfessorQueryDTO {

        private Long id;
        private String name;
        private String loginId;
        private String email;

        public ProfessorQueryDTO(Professor p) {
            id = p.getId();
            name = p.getName();
            loginId = p.getLoginId();
            email = p.getEmail();
        }
    }

}
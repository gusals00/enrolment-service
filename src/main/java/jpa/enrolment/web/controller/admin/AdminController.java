package jpa.enrolment.web.controller.admin;

import jpa.enrolment.domain.Period;
import jpa.enrolment.domain.PeriodName;
import jpa.enrolment.domain.person.Person;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.PeriodRepository;
import jpa.enrolment.repository.ProfessorRepository;
import jpa.enrolment.repository.StudentRepository;
import jpa.enrolment.service.ProfessorService;
import jpa.enrolment.service.ProfessorUpdateParam;
import jpa.enrolment.service.StudentService;
import jpa.enrolment.service.StudentUpdateParam;
import jpa.enrolment.web.controller.DepartmentChooseDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProfessorRepository professorRepository;
    private final ProfessorService professorService;
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final PeriodRepository periodRepository;

    @GetMapping("/")
    public String adminHome(){
        log.info("admin.AdminHomeController");
        return "/admin/adminHome";
    }

    @GetMapping("/professor")
    public String professorList(@ModelAttribute ProfessorSearch professorSearch, Model model){
        log.info("admin.ProfessorController.ProfessorList");
        List<QueryDTO> result;
        if(StringUtils.hasText(professorSearch.getName())){ //검색 조건 있는 경우
            result = getProfessorSimpleDTOS(professorSearch.getName());

        } else { //모든 교수 조회
            result =  getProfessorSimpleDTOS();
        }

        log.info("/admin/professor professorList size={}",result.size());
        model.addAttribute("professors",result);
        return "/admin/professor/professorList";
    }

    @GetMapping("/professor/{professorId}")
    public String professor(@PathVariable("professorId")Long id, Model model){
        ProfessorDetail professorDetail = new ProfessorDetail(professorRepository.findOneWithDepartment(id));
        model.addAttribute("professor",professorDetail);
        return "/admin/professor/professor";
    }

    @GetMapping("/professor/{professorId}/edit")
    public String editProfessorForm(@PathVariable("professorId")Long id,Model model){
        Professor findProfessor = professorRepository.findOne(id).get();

        List<DepartmentChooseDTO> departmentChooseList = changeDepartmentChooseDTOS();

        model.addAttribute("updateForm",new ProfessorUpdateParam(findProfessor));
        model.addAttribute("departmentList",departmentChooseList);

        return "/admin/professor/professorUpdateForm";
    }

    @PostMapping("/professor/{professorId}/edit")
    public String editProfessor(@PathVariable("professorId")Long id, @ModelAttribute("updateForm") ProfessorUpdateParam updateForm){

        professorService.update(id,updateForm);

        return "redirect:/admin/professor/{professorId}";
    }

    @PostMapping("/professor{professorId}/delete")
    public String deleteProfessor(@PathVariable("professorId")Long id){
        professorService.delete(id);
        return "redirect:/admin/professor";
    }

    @GetMapping("/student")
    public String StudentList(@ModelAttribute StudentSearch studentSearch, Model model) {
        List<QueryDTO> result;
        if(StringUtils.hasText(studentSearch.getName())){ //검색 조건 있는 경우
            result = getStudentSimpleDTOS(studentSearch.getName());

        } else { //모든 학생 조회
            result =  getStudentSimpleDTOS();
        }

        model.addAttribute("students",result);
        return "/admin/student/studentList";
    }

    @GetMapping("/student/{studentId}")
    public String student(@PathVariable("studentId") Long studentId, Model model) {
        StudentDetail studentDetail = new StudentDetail(studentRepository.findOneWithDepartment(studentId));
        model.addAttribute("student", studentDetail);
        return "/admin/student/student";
    }

    @GetMapping("/student/{studentId}/edit")
    public String editStudentForm(@PathVariable("studentId")Long id,Model model){
        Student findStudent = studentRepository.findOne(id).get();

        List<DepartmentChooseDTO> departmentChooseList = changeDepartmentChooseDTOS();

        model.addAttribute("updateForm",new StudentUpdateParam(findStudent));
        model.addAttribute("departmentList",departmentChooseList);

        return "/admin/student/studentUpdateForm";
    }

    @PostMapping("/student/{studentId}/edit")
    public String editStudent(@PathVariable("studentId")Long id, @ModelAttribute("updateForm") StudentUpdateParam updateForm){
        studentService.update(id, updateForm);

        return "redirect:/admin/student/{studentId}";
    }

    @PostMapping("/student/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId")Long id){
        studentService.deleteStudent(id);
        return "redirect:/admin/student";
    }


    @GetMapping("/syllabus-time")
    public String syllabusTimeForm(Model model){

        SyllabusTime syllabusTime;
        List<Period> byPeriodName = periodRepository.findByPeriodName(PeriodName.SYLLABUS);
        if (byPeriodName.isEmpty()){ // 설정된 강의계획서 입력기간이 없을 때
            syllabusTime = new SyllabusTime();
        }
        else{ // 설정된 강의계획서 입력기간이 있을 때
            Period period = byPeriodName.stream().findFirst().get();
            syllabusTime = new SyllabusTime(String.valueOf(period.getOpenTime().withNano(0)),String.valueOf(period.getCloseTime().withNano(0)));
        }


        model.addAttribute("syllabusTime",syllabusTime);
        return "/admin/syllabusTime";
    }

    @PostMapping("/syllabus-time")
    public String syllabusTimeEdit(@ModelAttribute("syllabusTime") SyllabusTime syllabusTime, Model model){

        log.info("syllabusTime : openTime={}, closeTime={}",syllabusTime.getOpenTime(),syllabusTime.getCloseTime());

        // entity에 넣어주면 됨
        return "redirect:/admin/";
    }


    public List<DepartmentChooseDTO> changeDepartmentChooseDTOS() {
        List<DepartmentChooseDTO> departmentChooseList = departmentRepository.findAll().stream()
                .map(d -> new DepartmentChooseDTO(d))
                .collect(Collectors.toList());
        return departmentChooseList;
    }

    public List<QueryDTO> getProfessorSimpleDTOS(String name) {
        return professorRepository.findByName(name)
                .stream()
                .map(p -> new QueryDTO(p))
                .collect(Collectors.toList());
    }

    public List<QueryDTO> getStudentSimpleDTOS(String name) {
        return studentRepository.findByName(name)
                .stream()
                .map(s -> new QueryDTO(s))
                .collect(Collectors.toList());
    }

    public List<QueryDTO> getProfessorSimpleDTOS() {
        return professorRepository.findAll()
                .stream()
                .map(p -> new QueryDTO(p))
                .collect(Collectors.toList());
    }

    public List<QueryDTO> getStudentSimpleDTOS() {
        return studentRepository.findAll()
                .stream()
                .map(p -> new QueryDTO(p))
                .collect(Collectors.toList());
    }

    @Getter @Setter
    static class QueryDTO {

        private Long id;
        private String name;
        private String loginId;
        private String email;

        public QueryDTO(Person p) {
            id = p.getId();
            name = p.getName();
            loginId = p.getLoginId();
            email = p.getEmail();
        }
    }

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class SyllabusTime{

        private String openTime;
        private String closeTime;

    }
}

package jpa.enrolment.web.controller;

import jpa.enrolment.domain.person.Person;
import jpa.enrolment.repository.AdminRepository;
import jpa.enrolment.repository.ProfessorRepository;
import jpa.enrolment.repository.StudentRepository;
import jpa.enrolment.web.argumentresolver.Login;
import jpa.enrolment.web.interceptor.SessionAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/")
@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    @GetMapping
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        log.info("login.home");
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, @Login SessionAuth sessionAuth ,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request){
        String loginId = loginForm.getLoginId();
        String loginPw = loginForm.getLoginPw();
        Long id = null;
        String dtype = null;

        if(sessionAuth.getPersonId()==null){
            if(adminRepository.login(loginId,loginPw)!=null){
                HttpSession session = request.getSession();
                //아이디, 타입
            }
            else if (studentRepository.login(loginId,loginPw)!=null){

            } else if(professorRepository.login(loginId,loginPw)!=null){

            }else{
                return "redirect:/";
            }
        }
        else{
            //해당 url로 넘겨주자??
        }
    }


    private void createSession(Person person){
        person.getId()
    }
}

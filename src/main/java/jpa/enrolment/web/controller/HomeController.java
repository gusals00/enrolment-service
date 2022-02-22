package jpa.enrolment.web.controller;

import jpa.enrolment.domain.person.Person;
import jpa.enrolment.repository.AdminRepository;
import jpa.enrolment.repository.ProfessorRepository;
import jpa.enrolment.repository.StudentRepository;
import jpa.enrolment.web.Mapper;
import jpa.enrolment.web.SessionConst;
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

    @GetMapping("/")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL){

        HttpSession session = request.getSession(false);


        if (session == null  ||  session.getAttribute(SessionConst.LOGIN_PERSON)==null) { // 세션 있을 때
            // 세션 없으면 로그인 페이지로 이동
            log.info("login.home");
            return "login";
        }
        SessionAuth sessionAuth = (SessionAuth) session.getAttribute(SessionConst.LOGIN_PERSON);
        return "redirect:" + Mapper.getRightURI(sessionAuth.getDtype());

    }

    @PostMapping("/")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, @Login SessionAuth sessionAuth , HttpServletRequest request){

        String loginId = loginForm.getLoginId();
        String loginPw = loginForm.getLoginPw();

        log.info("login id=[{}], pw=[{}]",loginId,loginPw);
        SessionAuth loginDTO = null;
        log.info("sessionAuth [{}],[{}]",sessionAuth.getPersonId(),sessionAuth.getDtype());
        if(sessionAuth.getPersonId()==null){ // 세션이 없을 때
            if(adminRepository.login(loginId,loginPw) != null){
                log.info("관리자 로그인");
                loginDTO = adminRepository.login(loginId,loginPw);
                createSession(request, loginDTO);
                //아이디, 타입
            } else if ( professorRepository.login(loginId,loginPw) != null){
                log.info("교수 로그인");
                loginDTO = professorRepository.login(loginId,loginPw);
                createSession(request, loginDTO);

            } else if(studentRepository.login(loginId,loginPw) != null){
                log.info("학생 로그인");
                loginDTO = studentRepository.login(loginId,loginPw);
                createSession(request, loginDTO);

            } else{
                return "redirect:/";
            }
        }

        return "redirect:" + Mapper.getRightURI(loginDTO.getDtype());
    }

    private void createSession(HttpServletRequest request, SessionAuth loginDTO) { // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_PERSON, loginDTO);
    }
}

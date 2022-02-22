package jpa.enrolment.web.interceptor;

import jpa.enrolment.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private Map<String, String> mapping = new HashMap<>();

    public LoginCheckInterceptor() {
        initMapping();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        String requestURI = request.getRequestURI();
        log.info("로그인 인터셉터 실행 [{}]",requestURI);

        if(session == null || session.getAttribute(SessionConst.LOGIN_PERSON) ==null){
            log.info("미인증 사용자 요청");

            response.sendRedirect("/");
            return false;
        }

        SessionAuth authAttribute = (SessionAuth)session.getAttribute(SessionConst.LOGIN_PERSON);
        if(isRightAccess(authAttribute.getDtype(),requestURI)){
            return true;
        }

        log.info("권한 없는 URI에 접근");
        response.sendRedirect(getRightURI(authAttribute.getDtype()));
        return false;

    }

    private boolean isRightAccess(String dtype,String requestURI) {
        return PatternMatchUtils.simpleMatch(mapping.get(dtype)+"*",requestURI);
    }

    private void initMapping(){
        mapping.put("admin ","/admin/");
        mapping.put("professor" ,"/professor/");
        mapping.put("student","/student/");
    }

    private String getRightURI(String dtype){
        return mapping.get(dtype);
    }
}

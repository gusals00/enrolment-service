package jpa.enrolment.web.argumentresolver;

import jpa.enrolment.web.SessionConst;
import jpa.enrolment.web.interceptor.SessionAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginPersonArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supports Parameter 실행");

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = SessionAuth.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument 샐행");

        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();

        HttpSession session = request.getSession(false);
        if(session == null){
            return null;
        }
        Object auth = (SessionAuth)session.getAttribute(SessionConst.LOGIN_PERSON);

        return auth;
    }
}

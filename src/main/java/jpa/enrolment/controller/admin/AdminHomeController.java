package jpa.enrolment.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminHomeController {

    @GetMapping("/admin")
    public String adminHome(){
        log.info("admin.AdminHomeController");
        return "/admin/adminHome";
    }
}

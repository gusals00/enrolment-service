package jpa.enrolment.web.interceptor;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SessionAuth {

    private Long personId;
    private String dtype;
}

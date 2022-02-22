package jpa.enrolment.web;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class Mapper {

    private final static Map<String, String> mapping = Map.of(
            "admin ","/admin/",
            "professor" ,"/professor/",
            "student","/student/"
    );

    public static String getRightURI(String dtype){
        return mapping.get(dtype);
    }
}

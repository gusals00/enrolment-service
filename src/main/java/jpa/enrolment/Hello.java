package jpa.enrolment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hello {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        Optional<Integer> first = list.stream().findFirst();
        System.out.println(first);
        first.get();
    }

}

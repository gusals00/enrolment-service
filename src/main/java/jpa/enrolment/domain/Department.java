package jpa.enrolment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    private int id;

    private int number;
    private String name;

    protected Department(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static Department createDepartment(int number, String name){
        return new Department(number,name);
    }
}



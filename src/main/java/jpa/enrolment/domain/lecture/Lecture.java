package jpa.enrolment.domain.lecture;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// 학생 조회, 수정 생성

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {

    @Id @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;

    private String code;
    private String name;
    private int level;
    private int credit;

    public Lecture() {
    }
}

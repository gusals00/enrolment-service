package jpa.enrolment.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class SyllabusWeekInfo {

    @Id @GeneratedValue
    @Column(name = "syllabus_info_id")
    private Long id;

    private int week;
    private String subject;
    private String content;
    private String assignment;
    private String evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;
}

package jpa.enrolment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Period {

    @Id @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private PeriodName periodName;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;
}

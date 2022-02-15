package jpa.enrolment.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Period {

    @Id @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private PeriodName periodName;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;
}

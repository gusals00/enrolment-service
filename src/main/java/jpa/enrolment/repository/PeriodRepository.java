package jpa.enrolment.repository;

import jpa.enrolment.domain.Period;
import jpa.enrolment.domain.PeriodName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PeriodRepository {

    private final EntityManager em;

    public List<Period> findByPeriodName(PeriodName periodName){
        return em.createQuery("select p from Period p where periodName =: name")
                .setParameter("name",periodName)
                .getResultList();
    }
}

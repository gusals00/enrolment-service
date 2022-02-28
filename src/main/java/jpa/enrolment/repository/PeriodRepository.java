package jpa.enrolment.repository;

import jpa.enrolment.domain.Period;
import jpa.enrolment.domain.PeriodName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    public Optional<Period> findById(Long id){
        return Optional.ofNullable(em.find(Period.class,id));
    }

    public Long savePeriod(Period period){
        em.persist(period);
        return period.getId();
    }
}

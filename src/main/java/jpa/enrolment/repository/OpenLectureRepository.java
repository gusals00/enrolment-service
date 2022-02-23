package jpa.enrolment.repository;

import jpa.enrolment.domain.Syllabus;
import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.domain.lecture.OpenLecture;
import jpa.enrolment.domain.person.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OpenLectureRepository {

    private final EntityManager em;

    public void save(OpenLecture openLecture) {
        em.persist(openLecture);
    }

    public Optional<OpenLecture> findOne(Long openLectureId) {
        return Optional.ofNullable(em.find(OpenLecture.class, openLectureId));
    }

    public List<OpenLecture> findByLectureIdAndSeperatedNumber(Long lectureId, int seperatedNumber) { // 교과목 id, 분반으로 개설교과목 찾기
        return em.createQuery("select ol from OpenLecture ol join ol.lecture l where l.id = :lectureId and ol.seperatedNumber = :seperatedNumber")
                .setParameter("lectureId", lectureId)
                .setParameter("seperatedNumber", seperatedNumber)
                .getResultList();
    }
}

package jpa.enrolment.service;

import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.domain.lecture.OpenLecture;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.repository.LectureRepository;
import jpa.enrolment.repository.OpenLectureRepository;
import jpa.enrolment.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OpenLectureService {

    private final OpenLectureRepository openLectureRepository;
    private final ProfessorRepository professorRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public void updateOpenLectureByAdmin(Long openLectureId, Long professorId, Long lectureId, int maxStudentNumber, int seperatedNumber, int curStudentNumber) {
        OpenLecture openLecture = openLectureRepository.findOne(openLectureId).orElseThrow(() -> new IllegalStateException("찾으려는 openLecture가 없음"));
        Professor professor = professorRepository.findOne(professorId).orElseThrow(() -> new IllegalStateException("찾으려는 professor가 없음"));
        Lecture lecture = lectureRepository.findOne(lectureId).orElseThrow(() -> new IllegalStateException("찾으려는 lecture가 없음"));

        checkDuplicatedSeperatedNumber(lectureId, seperatedNumber); // 중복시 예외 터트림(분반 중복 확인)

        // 교수 시간 중복 확인

        validateStudentNumber(maxStudentNumber, curStudentNumber);

        openLecture.changeLecture(lecture);
        openLecture.changeProfessor(professor);
        openLecture.changeNumber(seperatedNumber, maxStudentNumber, curStudentNumber);
    }

    @Transactional
    public void createOpenLecture(String lectureCode, Long professorId, int maxStudentNumber, int seperatedNumber, int curStudentNumber){

        Lecture lecture = lectureRepository.findByCode(lectureCode).orElseThrow(() -> new IllegalStateException("찾으려는 lecture가 없음"));
        Professor professor = professorRepository.findOne(professorId).orElseThrow(() -> new IllegalStateException("찾으려는 professor가 없음"));
        // 교수는 openlecture 만드는 창에서 이름 검색후 선택해서 id가 들어가게 만들 예정

        validateStudentNumber(maxStudentNumber, curStudentNumber);//최대인원<현재인원 확인

        // 교수 시간 중복 확인

        checkDuplicatedSeperatedNumber(lecture.getId(), seperatedNumber); // 분반 중복 확인

        OpenLecture openLecture = OpenLecture.builder()
                .lecture(lecture)
                .professor(professor)
                .curStudentNumber(curStudentNumber)
                .maxStudentNumber(maxStudentNumber)
                .seperatedNumber(seperatedNumber)
                .build();

        openLectureRepository.save(openLecture);
    }

    private void validateStudentNumber(int maxStudentNumber, int curStudentNumber) {
        if (curStudentNumber > maxStudentNumber) {
            throw new IllegalStateException("현재 학생수가 최대 학생수보다 많음");
        }
    }

    private void checkDuplicatedSeperatedNumber(Long lectureId, int seperatedNumber){
        List<OpenLecture> findOpenLectures = openLectureRepository.findByLectureIdAndSeperatedNumber(lectureId, seperatedNumber);
        if(!findOpenLectures.isEmpty())
            throw new IllegalStateException("존재하는 분반이 있습니다");
    }

    public void updateSyllabus() {

    }
}

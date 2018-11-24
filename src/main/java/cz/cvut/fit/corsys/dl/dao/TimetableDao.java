package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimetableDao extends JpaRepository<Timetable, Integer> {

    Timetable findTimetableByTimetableId(Integer id);

    List<Timetable> findTimetablesByDoctor(Doctor doctor);

    List<Timetable> findTimetablesByDoctorAndDateAfter(Doctor doctor, LocalDate date);

    List<Timetable> findTimetablesByDoctorAndDate(Doctor doctor, LocalDate date);

}

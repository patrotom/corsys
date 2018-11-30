package cz.cvut.fit.corsys.dl.dao;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimetableDao extends JpaRepository<Timetable, Integer> {

    /**
     * @param id
     * @return Timetable
     */
    Timetable findTimetableByTimetableId(Integer id);

    /**
     * Finds all timetables for given Doctor
     *
     * @param doctor
     * @return List of Timetables
     */
    List<Timetable> findTimetablesByDoctor(Doctor doctor);

    /**
     * Finds all upcoming timetables from given date, for given Doctor
     *
     * @param doctor
     * @param date
     * @return List of Timetables
     */
    List<Timetable> findTimetablesByDoctorAndDateAfter(Doctor doctor, LocalDate date);

    /**
     * Finds all timetables for given day, for Doctor
     *
     * @param doctor
     * @param date
     * @return List of Timetables
     */
    List<Timetable> findTimetablesByDoctorAndDate(Doctor doctor, LocalDate date);

}

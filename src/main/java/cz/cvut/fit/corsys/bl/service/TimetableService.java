package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;

import java.time.LocalDate;
import java.util.List;

public interface TimetableService {

    List<Timetable> findAllTimetables();

    List<Timetable> findTimetables(Doctor doctor);

    List<Timetable> findTimetablesSince(Doctor doctor, LocalDate date);

    List<Timetable> findTimetablesForDate(Doctor doctor, LocalDate date);

    void addTimetable(Timetable timetable);

    void deleteTimetable(Timetable timetable);

}

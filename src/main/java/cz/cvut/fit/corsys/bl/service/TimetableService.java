package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Timetable;

import java.time.LocalDate;
import java.util.List;

public interface TimetableService {

    /**
     * Finds all timetables.
     *
     * @return list of all timetables.
     */
    List<Timetable> findAllTimetables();

    /**
     * Finds all timetables belonging to the specified doctor.
     *
     * @param doctor the doctor for whom to find timetables.
     * @return list of found timetables.
     */
    List<Timetable> findTimetables(Doctor doctor);

    /**
     * Finds all timetables belonging to the specified doctor since the specified day.
     *
     * @param doctor the doctor for whom to find timetables.
     * @param date   the day since which to find timetables (including).
     * @return list of found timetables.
     */
    List<Timetable> findTimetablesSince(Doctor doctor, LocalDate date);

    /**
     * Finds timetables for specified day and doctor.
     *
     * @param doctor the doctor for whom to find timetables.
     * @param date   the day for which to find timetables.
     * @return list of found timetables.
     */
    List<Timetable> findTimetablesForDate(Doctor doctor, LocalDate date);

    /**
     * Creates a timetable.
     *
     * @param timetable the timetable to be created.
     * @return the created timetable. Use this object for further operations.
     */
    Timetable createTimetable(Timetable timetable);

    /**
     * Deletes the specified timetable.
     *
     * @param timetable the timetable to be deleted.
     * @throws IllegalArgumentException in case that the specified timetable does not exist.
     */
    void deleteTimetable(Timetable timetable) throws IllegalArgumentException;

}

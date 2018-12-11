package cz.cvut.fit.corsys.bl.service;

import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Timetable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Defines basic interface, which class have to implement, in order to communicate with data layer,
 *  should be used for general operations on user accounts, such as authentication
 *  and manipulation with them. All other operations should be performed directly on service
 *  interfaces intended for respective roles.
 */

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

    /**
     * Returns length of timetable interval as a count of 15-minute intervals included.
     *
     * @param timetable the timetable for which to determine length.
     * @return the length of the timetable.
     */
    Integer getTimetableLength(Timetable timetable);

    /**
     * Subtracts time interval from specified timetables. Counts new list of timetables according
     * to modifications needed.
     *
     * @param timetables list of previous timetables.
     * @param from       beginning of the interval.
     * @param to         end of the interval.
     * @return modified list of timetables.
     */
    List<Timetable> subtractTimeInterval(List<Timetable> timetables, LocalTime from, LocalTime to);

}

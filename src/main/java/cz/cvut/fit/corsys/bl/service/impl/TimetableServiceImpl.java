package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.TimetableService;
import cz.cvut.fit.corsys.dl.dao.TimetableDao;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableDao timetableDao;

    @Override
    public List<Timetable> findAllTimetables() {
        return timetableDao.findAll();
    }

    @Override
    public List<Timetable> findTimetables(Doctor doctor) {
        return timetableDao.findTimetablesByDoctor(doctor);
    }

    @Override
    public List<Timetable> findTimetablesSince(Doctor doctor, LocalDate date) {
        List<Timetable> timetablesToday = timetableDao.findTimetablesByDoctorAndDate(doctor, date);
        List<Timetable> timetablesNext = timetableDao.findTimetablesByDoctorAndDateAfter(doctor, date);
        List<Timetable> newList = new ArrayList<Timetable>(timetablesToday);
        newList.addAll(timetablesNext);
        return newList;
    }

    @Override
    public List<Timetable> findTimetablesForDate(Doctor doctor, LocalDate date) {
        return timetableDao.findTimetablesByDoctorAndDate(doctor, date);
    }

    @Override
    public Timetable createTimetable(Timetable timetable) {
        return timetableDao.save(timetable);
    }

    @Override
    public void deleteTimetable(Timetable timetable) throws IllegalArgumentException {
        if (timetableDao.findTimetableByTimetableId(timetable.getTimetableId()) == null) {
            throw new IllegalArgumentException();
        }
        timetableDao.delete(timetable);
    }

    @Override
    public Integer getTimetableLength(Timetable timetable) {
        long elapsedMinutes = Duration.between(timetable.getTimeFrom(), timetable.getTimeTo()).toMinutes();
        return (int) elapsedMinutes / Timetable.TIME_DIV;
    }

    @Override
    public List<Timetable> subtractTimeInterval(List<Timetable> timetables, LocalTime from, LocalTime to) {
        List<Timetable> newTimetables = new ArrayList<>();
        for (Timetable t : timetables) {
            LocalTime tFrom = t.getTimeFrom();
            LocalTime tTo = t.getTimeTo();
            if (from.isBefore(tFrom) && to.isAfter(tFrom) && (to.isBefore(tTo) || to.equals(tTo))) {
                // time overlaps (from side)
                t.setTimeFrom(to);
                newTimetables.add(t);
            } else if ((from.isAfter(tFrom) || from.equals(tFrom)) && (to.isBefore(tTo) || to.equals(tTo))) {
                // time is included
                Timetable t1 = new Timetable();
                t1.setDoctor(t.getDoctor());
                t1.setDate(t.getDate());
                t1.setTimeFrom(tFrom);
                t1.setTimeTo(from);
                newTimetables.add(t1);

                Timetable t2 = new Timetable();
                t2.setDoctor(t.getDoctor());
                t2.setDate(t.getDate());
                t2.setTimeFrom(to);
                t2.setTimeTo(tTo);
                newTimetables.add(t2);
            } else if ((from.isAfter(tFrom) || from.equals(tFrom)) && from.isBefore(tTo) && to.isAfter(tTo)) {
                // time overlaps (to side)
                t.setTimeTo(from);
                newTimetables.add(t);
            } else {
                newTimetables.add(t);
            }
        }
        return newTimetables;
    }
}

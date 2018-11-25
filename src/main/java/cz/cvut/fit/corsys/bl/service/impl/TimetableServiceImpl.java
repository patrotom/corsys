package cz.cvut.fit.corsys.bl.service.impl;

import cz.cvut.fit.corsys.bl.service.TimetableService;
import cz.cvut.fit.corsys.dl.dao.TimetableDao;
import cz.cvut.fit.corsys.dl.entity.Doctor;
import cz.cvut.fit.corsys.dl.entity.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
}

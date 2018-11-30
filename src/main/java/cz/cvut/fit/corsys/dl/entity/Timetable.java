package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Contains information about working hours defined by doctors.
 * Each entity class must represent some particular day.
 * System will be capable of determining when a doctor is or is not working.
 *
 * @author fabosamu
 */
@Entity
public class Timetable {

    public static final Integer TIME_DIV = 15;

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timetableId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime timeFrom;

    @Column(nullable = false)
    private LocalTime timeTo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    public Integer getTimetableId() {
        return timetableId;
    }

    /**
     * @param timetableId
     */
    public void setTimetableId(Integer timetableId) {
        this.timetableId = timetableId;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    /**
     * @param timeFrom
     */
    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    /**
     * @param timeTo
     */
    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
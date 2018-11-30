package cz.cvut.fit.corsys.dl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Carries information about particular reservation.
 * Each row defines some aspect of the reservation, including particular date and time interval
 * (representing "Termín" domain from the package "Doménový model").
 *
 * @author fabosamu
 */
@Entity
public class Reservation {

    /**
     * Predefined states of the Reservation
     */
    public static final String STATE_UNCONFIRMED = "UNCONFIRMED";
    public static final String STATE_CONFIRMED = "CONFIRMED";
    public static final String STATE_CANCELED = "CANCELED";

    /**
     * Unique identifier of the entity class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime timeFrom;

    @Column(nullable = false)
    private LocalTime timeTo;

    @Column(length = 50, nullable = false)
    private String state;

    @Column(nullable = true)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "examinationId")
    private Examination examination;


    public Integer getReservationId() {
        return reservationId;
    }

    /**
     * @param reservationId
     */
    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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

    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public Examination getExamination() {
        return examination;
    }

    /**
     * @param examination
     */
    public void setExamination(Examination examination) {
        this.examination = examination;
    }
}
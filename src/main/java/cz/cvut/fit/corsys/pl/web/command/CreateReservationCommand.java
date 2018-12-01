package cz.cvut.fit.corsys.pl.web.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CreateReservationCommand {

    private Integer departmentId;

    private Integer examinationId;

    private Integer doctorId;

    private String patientUsername;

    private String date;

    private String timeFrom;

    private LocalTime timeTo;

    private String state;

    private String description;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    @Override
    public String toString() {
        return "CreateReservationCommand{" +
                "departmentId=" + departmentId +
                ", examinationId=" + examinationId +
                ", doctorId=" + doctorId +
                ", patientUsername='" + patientUsername + '\'' +
                ", date='" + date + '\'' +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

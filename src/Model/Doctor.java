package Model;

import java.util.Date;

public class Doctor {
    private int id;
    private String doctorName;
    private String speciality;
    private String schedule;
    public Doctor(String doctorName, String speciality, String schedule) {
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.schedule = schedule;
    }
    public Doctor(int id, String doctorName, String speciality, String schedule) {
        this.id = id;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.schedule = schedule;
    }
    public int getId() {
        return id;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public String getSpeciality() {
        return speciality;
    }
    public String getSchedule() {
        return schedule;
    }
}

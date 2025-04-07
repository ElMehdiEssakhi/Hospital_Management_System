package Model;

import java.sql.Date;
import java.sql.Time;

public class RendezVous {
    private int id;
    private String patientFileNum;
    private int DoctorId;
    private java.sql.Date date;
    private Time time;
    public RendezVous(String patientFileNum, int DoctorId, java.sql.Date date, Time time) {
        this.patientFileNum = patientFileNum;
        this.DoctorId = DoctorId;
        this.date = date;
        this.time = time;
    }
    public RendezVous(int id, String patientFileNum, int DoctorId, java.sql.Date date, Time time) {
        this.id = id;
        this.patientFileNum = patientFileNum;
        this.DoctorId = DoctorId;
        this.date = date;
        this.time = time;
    }
    public int getId() {
        return id;
    }
    public String getPatientFileNum() {
        return patientFileNum;
    }
    public int getDoctorId() {
        return DoctorId;
    }
    public Date getDate() {
        return date;
    }
    public Time getTime() {return time;}
}

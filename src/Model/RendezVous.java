package Model;

import java.sql.Date;

public class RendezVous {
    private int id;
    private int patientId;
    private int DoctorId;
    private java.sql.Date date;
    public RendezVous(int patientId, int DoctorId, java.sql.Date date) {
        this.patientId = patientId;
        this.DoctorId = DoctorId;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public int getPatientId() {
        return patientId;
    }
    public int getDoctorId() {
        return DoctorId;
    }
    public Date getDate() {
        return date;
    }
}

package Model;

import java.sql.Date;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private java.sql.Date dateOfBirth;
    private String phoneNumber;
    private String FileNum;
    public Patient(String firstName, String lastName, java.sql.Date dateOfBirth, String phoneNumber, String FileNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.FileNum = FileNum;
    }
    public Patient(int id, String firstName, String lastName, java.sql.Date dateOfBirth, String phoneNumber, String fileNum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.FileNum = fileNum;
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getFileNum() {
        return FileNum;
    }
}

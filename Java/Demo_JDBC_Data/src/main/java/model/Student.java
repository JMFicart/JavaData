package model;

import java.time.LocalDate;

//@ToString(exclude = "responsableDe")
public class Student {
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate date;
    private Section responsable;

    private Student() {}

    public Student(long id, String firstname, String lastname, LocalDate date, Section section) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.responsable = section;
    }

    public static StudentBuilder builder(){
        return new StudentBuilder();
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getDate() {
        return date;
    }

    public Section getResponsable() {
        return responsable;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setResponsable(Section responsable) {
        this.responsable = responsable;
    }
}

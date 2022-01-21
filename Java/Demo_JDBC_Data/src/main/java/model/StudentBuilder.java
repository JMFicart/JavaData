package model;

import java.time.LocalDate;
import java.sql.Date;

public class StudentBuilder {
    private int id;
//    private String firstname = "Inconnu";
//    private String lastname;
    private LocalDate birthdate;
    private String login;
    private String courseId;
    private int yearRslt;
    private Section section;

//    public void test() {
//        StudentBuilder student = new StudentBuilder().firstname("Luc").id(15);
//    }

    // constructor chainé
    public StudentBuilder id(int id) {
        this.id = id;
        return this;
    }

//    public StudentBuilder firstname(String firstname) {
//        this.firstname = firstname;
//        return this;
//    }
//
//    public StudentBuilder lastname(String lastname) {
//        this.lastname = lastname;
//        return this;
//    }

    public StudentBuilder birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public StudentBuilder birthdate(Date birthdate) {
        this.birthdate = birthdate.toLocalDate();
        return this;
    }

    public StudentBuilder login(String login) {
        this.login = login;
        return this;
    }
    public StudentBuilder courseid(String courseid) {
        this.courseId = courseid;
        return this;
    }

    public StudentBuilder yearRslt(int yearRslt) {
        this.yearRslt = yearRslt;
        return this;
    }

    public StudentBuilder section(Section section) {
        this.section = section;
        return this;
    }

    public Student build(String nom, String prenom) {
        return new Student(id,
                            nom,
                            prenom,
                            birthdate,
                            section);
    }
}


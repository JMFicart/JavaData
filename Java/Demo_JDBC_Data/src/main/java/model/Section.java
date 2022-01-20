package model;

public class Section {
    private long id;
    private String nom;
    private Student delegue;

    public Section() {
    }

    public Section(long id, String nom, Student delegue) {
        this.id = id;
        this.nom = nom;
        this.delegue = delegue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Student getDelegue() {
        return delegue;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDelegue(Student delegue) {
        this.delegue = delegue;
    }
}

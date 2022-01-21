package model;

public class Professor {
    private int id;
    private String firstname;
    private String lastname;
    private Section section;
    private int wage;

    public Professor() {}

    public Professor(int id, String firstname, String lastname, int wage, Section section) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.wage = wage;
        this.section = section;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public int getWage(){
        return wage;
    }

    public Section getSection(){
        return section;
    }

    public static class Section {
        private int id;
        private String name;

        private Section() {}

        public Section(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName(){
            return name;
        }
    }
}

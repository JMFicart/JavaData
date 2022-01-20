import data_access.ConnectionFactory;
import data_access.SectionDAO;
import model.Student;
import model.Section;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
//        DEMO 1
//        String qry = "select * from section";
//        try (   Connection co = data_access.ConnectionFactory.getConnection();
//                Statement stmt = co.createStatement();
//                ResultSet rs = stmt.executeQuery(qry);)
//        {
//            while (rs.next()) {
//                int sectionId = rs.getInt("section_Id");
//                String sectionName = rs.getString("section_Name");
//                int delegateId = rs.getInt("delegate_Id");
//                System.out.println(sectionId + " " + sectionName + " " + delegateId);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

//        StudentBuilder builder = Student.builder().id(1).login("abcd");

//      DEMO 2
//        Section s = getSection(1110);
//        if (s != null) {
//            System.out.println("------------ SECTION ------------");
//            System.out.println(s.getNom());
//            System.out.println(s.getDelegue().getFirstname());
//            System.out.println(s.getDelegue().getLastname());
//            System.out.println(s.getDelegue().getId());
//            System.out.println(s.getDelegue().getDate());
//        }
        SectionDAO dao = new SectionDAO();
        Section section = new Section(999, "Programmation", null);
        System.out.println("Insert done : " + dao.insert(section));
        System.out.println("Delete done : " + dao.deleteStartWith('P'));
    }

    public static Section getSection(int id) {
        String qry = "select S.section_id as id, section_name, first_name, last_name, birth_date, student_id " +
                        "from section S inner join student E on S.delegate_id = E.student_id " +
                        "where S.section_id = " + id;

        try(
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(qry);
                )
        {
            if (rs.next()) {
                // créer section
                Section s = new Section(); // = null;

                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("section_name"));

                Student st = Student.builder()
                        .section(s)
                        .birthdate(rs.getDate("birth_date"))
                        .id(rs.getInt("student_id"))
                        .build(
                                rs.getString("first_name"),
                                rs.getString("last_name")
                        );
                s.setDelegue(st);

                return s;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

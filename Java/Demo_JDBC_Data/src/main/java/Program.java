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

//        DEMO 2
//        Section s = getSection(1110);
//        if (s != null) {
//            System.out.println("------------ SECTION ------------");
//            System.out.println(s.getNom());
//            System.out.println(s.getDelegue().getFirstname());
//            System.out.println(s.getDelegue().getLastname());
//            System.out.println(s.getDelegue().getId());
//            System.out.println(s.getDelegue().getDate());
//        }
//        SectionDAO dao = new SectionDAO();
//        Section section = new Section(999, "Programmation", null);
//        System.out.println("Insert done : " + dao.insert(section));
//        System.out.println("Delete done : " + dao.deleteStartWith('P'));

//        DEMO 3
//        SectionDAO dao = new SectionDAO();
//        Student student = null;
//
//        System.out.println("------------- insert ----------------------");
//        Section section = new Section(6666, "SQL 3", student);
//        dao.insertBySP2(section);

//        System.out.println("------------- get all ----------------------");
//        dao.getAll().forEach(System.out::println);
//
//        System.out.println("------------- update ----------------------");
//        dao.update(9999, "Programmation Java", 7);
//
//        System.out.println("------------- get all ----------------------");
//        dao.getAll().forEach(System.out::println);
//
//        System.out.println("------------- delete ----------------------");
//        dao.deleteWithId(9999);

//        TODO : DEMO 5
        SectionDAO dao = new SectionDAO();
        dao.updateBizarre();
//
    }
}

import data_access.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class ListeEtudiant {
    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);

        try {
            System.out.print("Entrez le nom de la section : ");
            int sectionid = es.nextInt();
            String qry = "select E.student_id, E.first_name, E.last_name, E.birth_date, S.section_name " +
                    "from student E inner join section S on E.section_id = S.section_id " +
                    "where S.section_id = '" + sectionid + "' " +
                    "order by E.student_id";
            lister(qry);
        } catch (NumberFormatException ex) {
        }
    }

    private static void lister(String qry){
        try (Connection co = ConnectionFactory.getConnection();
             Statement stmt = co.createStatement();
             ResultSet rs = stmt.executeQuery(qry);)
        {
            while (rs.next()) {
                int studentId = rs.getInt("student_Id");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String sectionName = rs.getString("section_Name");
                Date birthdate = rs.getDate("birth_date");

                System.out.println("-----------------------------");
                System.out.println("Id           : " + studentId);
                System.out.println("First name   : " + firstname);
                System.out.println("Last name    : " + lastname);
                System.out.println("Birthdate    : " + birthdate);
                System.out.println("Section name : " + sectionName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

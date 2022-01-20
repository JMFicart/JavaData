package data_access;

import model.Section;
import model.Student;

import java.sql.*;
import java.util.List;

public class SectionDAO {
    // Create
    public boolean insert(Section toInsert){
        String qry = "insert into section (section_id, section_name) " +
                        "values (?, ?)";
        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setLong(1, toInsert.getId());
            stmt.setString(2, toInsert.getNom());
            return 1 == stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int deleteselonnom(String name){
//        String qry = "delete from section where section_name = ?";
        String qry = "delete from section where section_name like ?%";
        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setString(1, name);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteStartWith(char start) {
        if ((start < 'a' || start > 'z') && (start < 'A' || start >'Z')) {
            throw new IllegalArgumentException("start doit être une lettre !");
        }
        String qry = "delete from section where section_name like ?";
        try (Connection co = ConnectionFactory.getConnection();
             PreparedStatement stmt = co.prepareStatement(qry);) {
            stmt.setString(1,  start + "%");
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // Read
    public  Section getOne(int id) {return  null;}
    public List<Section> getAll(){
        return null;
    }
}


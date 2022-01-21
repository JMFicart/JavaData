package data_access;

import model.Section;
import model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SectionDAO {
    // Create
    public boolean insert(Section toInsert){
        String qry = "insert into section (section_id, section_name) " +
                        "values (?, ?)";

        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setInt(1, toInsert.getId());
            stmt.setString(2, toInsert.getNom());
            return 1 == stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int deleteselonnom(String name){
//        String qry = "delete from section where section_name = ?";
        String qry = "delete from section where section_name like ?";
        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setString(1, name + '%');
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

    // Get one
    public Section getOne(int id){
        String qry = "select S.section_id, section_name, first_name, last_name, birth_date, student_id " +
                "from section S left outer join student E on S.delegate_id = E.student_id " +
                "where S.section_id = " + id;

        try(
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(qry);
        )
        {
            if (rs.next()) {
                return extract(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Get all
    public List<Section> getAll(){
        String qry = "select S.section_id, S.section_name, " +
                "E.student_id, E.first_name, E.last_name, E.birth_date " +
                "from section S left outer join student E on S.delegate_id = E.student_id " +
                "order by S.section_id;";

        try(
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(qry);
        )
        {
            List<Section> sections = new ArrayList<>();
            while (rs.next()) {
                sections.add(extract(rs));
            }
            return sections;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Delete with id
    public boolean deleteWithId(int id){
        String qry = "delete from section where section_id = ?";
        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setInt(1, id);
            return 1 == stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Update
    public boolean update(int id, String newname, int newDelegateId){
        String qry = "update section set section_name = ?, delegate_id = ? " +
                        "where section_id = ?";
        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setString(1, newname);
            stmt.setInt(2, newDelegateId);
            stmt.setInt(3, id);
            return 1 == stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int updateBizarre(){
        String qry = "select * from student order by birth_date";
        try(Connection co = ConnectionFactory.getConnection();
            // resultsettype
            Statement stmt = co.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(qry);
        ){int ligneModified = 0;
            rs.last();
            do {
                Date birthdate = rs.getDate("birth_date");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                if (birthdate.getYear() >= 1978) {
                    rs.updateString("login", (firstname + "_" + lastname).toLowerCase());
                    rs.updateRow();
                    ligneModified++;
                }
            } while(rs.previous());
            return ligneModified;
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            return 0;
        }
    }

    private Section extract(ResultSet rs) throws SQLException{
        // créer section
        Section s = new Section();

        s.setId(rs.getInt("section_id"));
        s.setNom(rs.getString("section_name"));

        long stId = rs.getLong("student_id");
        Student st = null;

        if(stId != 0){
            Student.builder()
                .section(s)
                .birthdate(rs.getDate("birth_date"))
                .id(rs.getInt("student_id"))
                .build(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
        }
        s.setDelegue(st);
        return s;
    }

//    TODO : DEMO4
    public void insertBySP(Section toInsert){
        String qry = "call sp_insert_section (?,?,?)";

        try (Connection co = ConnectionFactory.getConnection();
             PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setLong(1, toInsert.getId());
            stmt.setString(2, toInsert.getNom());
            if(toInsert.getDelegue() != null){
                stmt.setLong(3, toInsert.getDelegue().getId());
            }
            else {
                stmt.setNull(3,Types.INTEGER);
            }

            boolean res = stmt.execute();
            if (res){
                stmt.getResultSet();
            } else
            {
                stmt.getUpdateCount();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertBySP2(Section toInsert){
        String qry = "call sp_insert_section (?,?,?,?)";

        try (Connection co = ConnectionFactory.getConnection();
             CallableStatement stmt = co.prepareCall(qry);)
        {
            stmt.setLong(1, toInsert.getId());
            stmt.setString(2, toInsert.getNom());
            if(toInsert.getDelegue() != null){
                stmt.setLong(3, toInsert.getDelegue().getId());
            }
            else {
                stmt.setNull(3,Types.INTEGER);
            }

            stmt.execute();
            int a = stmt.getInt("rslt");
            System.out.println(a);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


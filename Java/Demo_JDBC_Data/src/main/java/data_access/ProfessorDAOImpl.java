package data_access;

import model.Professor;
import model.Section;
import model.Student;

import java.sql.*;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {

    @Override
    public boolean insert(Professor toInsert) {
        String qry = "insert into professor (professor_id, professor_name, professor_surname, section_id, professor_wage) " +
                "values (?, ?, ?, ?, ?)";

        try(Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(qry);)
        {
            stmt.setInt(1, toInsert.getId());
            stmt.setString(2, toInsert.getFirstname());
            stmt.setString(3, toInsert.getLastname());
            stmt.setInt(4, toInsert.getSection().getId());
            stmt.setInt(5, toInsert.getWage());
            return 1 == stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Professor> getAll() {
        return null;
    }

    @Override
    public List<Professor> getAllWithAverageResultBiggerThan(int result) {
        return null;
    }

    @Override
    public Professor getOne(int id) {
        String qry = "select P.professor_id, P.professor_name, P.professor_surname, P.section_id, P.professor_wage, " +
                "S.student_id, S.first_name, S.last_name, S.year_result " +
                "from professor P left outer join student S on P.section_id = S.section_id " +
                "where P.professor_id = " + id;
        try (
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

    private Professor extract(ResultSet rs) throws SQLException{
        // créer professor
        Professor p = new Professor();

        p.setId(rs.getInt("professor_id"));
        p.setFirstname(rs.getString("professor_name"));
        p.setLastname(rs.getString("professor_surname"));
        p.setSection(rs.getInt("section_id"));
        p.setWage(rs.getInt("professor_wage"));

        int stId = rs.getInt("student_id");
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

    @Override
    public boolean update(int id, Professor newValues) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public int separateProfInSections() {
        return 0;
    }
}

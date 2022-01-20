import data_access.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args){
        try (Connection co = ConnectionFactory.getConnection()){
            System.out.println("Connection successfull");
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }
    }
}
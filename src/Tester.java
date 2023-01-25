import java.sql.*;
import java.util.ArrayList;

public class Tester {

    static final String DB_NAME = "newdb";
    static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    static final String USER = "root";
    static final String PASSWORD = "060891";

    public static void main(String[] args){

        try(Connection connection = DriverManager.getConnection (URL,USER,PASSWORD );
        Statement statement = connection.createStatement()){

            statement.executeUpdate ( "CREATE VIEW italian_students AS SELECT first_name, last_name WHERE country = Italy");
            statement.executeUpdate ( "CREATE VIEW german_students AS SELECT first_name, last_name WHERE country = Germany");

            ArrayList<Student> italianStudents = new ArrayList<>();
            ResultSet italianResulSet = statement.executeQuery ("SELECT * FROM italian_students");

            while(italianResulSet.next()){
                italianStudents.add ( new Student ( italianResulSet.getString ( "first_name" ), italianResulSet.getString("last_name") ) );

            }

            ArrayList<Student> germanStudent = new ArrayList<> ();
            ResultSet germanResulSet = statement.executeQuery ( "SELECT * FROM german_students");

            while(germanResulSet.next ()){
                germanStudent.add ( new Student ( germanResulSet.getString ( "first_name"), germanResulSet.getString ( "last_name" ) ) );
            }




        }catch (SQLException e){

        }

    }
}

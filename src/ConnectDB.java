import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
    public static void main(String[] args) {
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";
        try{
            // Step 1: load driver to file
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 2: establish connection
            Connection con = DriverManager.getConnection(dbURL,user,pass);
            // Step 3: create statement that make statement object can understand sql
            Statement st = con.createStatement();
            // Step 4: run the query
            st.execute("insert into student(id,lastname,firstname,class)" + "value(101,'Visal','Yun','B6B')");
            ResultSet rs = st.executeQuery("select * from student");
            while(rs.next()){
                String id = rs.getString("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String classes = rs.getString("class");
                System.out.println(id + ":" + lastname +":"+firstname + ":" + classes);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}

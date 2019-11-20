import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Dynamic_Input {
    public static void main(String[] args) {
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        Scanner s = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL,user,pass);
            Statement st = con.createStatement();

            System.out.print("Enter ID: ");
            int i = s.nextInt();
            System.out.print("Enter lastname: ");
            String lname = s.next();
            System.out.print("Enter firstname: ");
            String fname = s.next();
            System.out.print("Enter class: ");
            String c = s.next();
            st.execute("insert into student(id,lastname,firstname,class) values ('"+i+"', '"+lname+"', '"+fname+"', '"+c+"')");
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

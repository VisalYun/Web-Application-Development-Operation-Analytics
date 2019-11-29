import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BatchUpdateProgram {
    public static void main(String[] args) {
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        Scanner s = new Scanner(System.in);
        int num;

        try{
            Connection con = DriverManager.getConnection(dbURL, user, pass);

            PreparedStatement ps = con.prepareStatement("insert into student values (?,?,?,?)");
            System.out.println("Enter total number of records: ");
            num = s.nextInt();

            for(int i=0; i<num; i++){
                System.out.println("Enter id, lastname, firstname, class: ");
                int id = s.nextInt();
                String lname = s.next();
                String fname = s.next();
                String classs = s.next();
                ps.setInt(1,id);
                ps.setString(2, lname);
                ps.setString(3, fname);
                ps.setString(4, classs);

                ps.addBatch();
            }
            ps.executeBatch();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

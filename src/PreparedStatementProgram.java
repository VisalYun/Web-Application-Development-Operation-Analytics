import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PreparedStatementProgram {
    public static void main(String[] args) {
        student stu = new student();
        Scanner s = new Scanner(System.in);

        System.out.println("Enter id, last name, first name, class: ");
        stu.setID(s.nextInt());
        stu.setLastname(s.next());
        stu.setFirstname(s.next());
        stu.setClass(s.next());

        PreparedStatementProgram obj = new PreparedStatementProgram();
        obj.insertRecord(stu);
    }

    public void insertRecord(student stu){
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL,user,pass);
            PreparedStatement ps = con.prepareStatement("insert into student values (?,?,?,?)");
            ps.setInt(1, stu.getI());
            ps.setString(2, stu.getLname());
            ps.setString(3, stu.getFname());
            ps.setString(4, stu.getC());

            ps.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

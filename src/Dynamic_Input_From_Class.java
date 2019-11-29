import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Dynamic_Input_From_Class {
    public static void main(String[] args) {
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        Scanner s = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL,user,pass);
            Statement st = con.createStatement();

            student stu = new student();

            System.out.print("Enter ID: ");
            int i = s.nextInt();
            stu.setID(i);

            System.out.print("Enter lastname: ");
            String lname = s.next();
            stu.setLastname(lname);

            System.out.print("Enter firstname: ");
            String fname = s.next();
            stu.setFirstname(fname);

            System.out.print("Enter class: ");
            String c = s.next();
            stu.setClass(c);

            st.execute("insert into student(id,lastname,firstname,class) values ('"+stu.getI()+"', '"+stu.getLname()+"', '"+stu.getFname()+"', '"+stu.getC()+"')");
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

class student{
    //variable should be private
    //should have get set function
    private int i;
    private String lname;
    private String fname;
    private String c;
    Connection con;
    Statement st;
    String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "";

    public void getStatement(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, user, pass);
            st = con.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setID(int id){
        this.i = id;
    }

    public void setLastname(String lastname){
        this.lname = lastname;
    }

    public void setFirstname(String firstname){
        this.fname = firstname;
    }

    public void setClass(String Class){
        this.c = Class;
    }

    public int getI() {
        return i;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getC() {
        return c;
    }
}

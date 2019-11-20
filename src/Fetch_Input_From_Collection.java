import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fetch_Input_From_Collection {
    public static void main(String[] args) {
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        int in;
        ArrayList<student> list = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        System.out.print("Enter i: ");
        in = s.nextInt();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL,user,pass);
            Statement st = con.createStatement();

            student stu = new student();
            while (in>0){
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

                list.add(stu);

                in--;

                for(student student_element:list){
                    st.execute("insert into student(id,lastname,firstname,class) values ('"+stu.getI()+"', '"+stu.getLname()+"', '"+stu.getFname()+"', '"+stu.getC()+"')");
                }
            }

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

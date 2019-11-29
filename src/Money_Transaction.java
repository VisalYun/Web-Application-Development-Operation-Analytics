import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Money_Transaction {
    public static void main(String[] args) {
        new Money_Transaction().Transact();
    }

    public  void Transact() {
        String dbURL = "jdbc:mySQL://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        int senderID, receiverID, amount;

        Scanner s = new Scanner(System.in);

        System.out.println("Enter Your ID: ");
        senderID = s.nextInt();

        System.out.println("Enter the ID of users you want to send: ");
        receiverID = s.nextInt();

        System.out.println("Enter the amount of money: ");
        amount = s.nextInt();

        try{
            Connection con = DriverManager.getConnection(dbURL, user, pass);
            con.setAutoCommit(false);
            try{
                Statement st = con.createStatement();
                st.execute("update bank set balance = balance - '"+amount+"' where id = '"+senderID+"'");
                st.execute("update bank set balance = balance + '"+amount+"' where id = '"+receiverID+"'");

                con.commit();

                System.out.println("Transaction completed! Thank you for using our service...");
            }catch (Exception e){
                con.rollback();
                System.out.println(e);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}

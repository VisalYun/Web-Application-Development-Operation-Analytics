import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Insert_In_Different_Class {
    private static Statement st;
    public static void main(String[] args) {

        int input, option, ViewId;
        ArrayList<student> dtolist = new ArrayList<>();

        System.out.println("Option 1: Insert single report: ");
        System.out.println("Option 2: Insert more report: ");
        System.out.println("Option 3: Read single report: ");
        System.out.println("Option 4: Read all report: ");
        System.out.println("Option 5: Read all matching report: ");
        Scanner s = new Scanner(System.in);
        System.out.print("Enter option: ");
        option = s.nextInt();

        student stu = new student();
        stu.getStatement();

        switch (option){
            case 1:
                inputUser(stu, s);
                insertRecord(stu);
                break;
            case 2:
                System.out.print("Enter i: ");
                input = s.nextInt();

                while (input>0){
                    dtolist.add(stu);
                    insertRecordCollection(dtolist);
                    input--;
                }
                break;
            case 3:
                System.out.println("Enter ID that you want to view: ");
                ViewId = s.nextInt();
                readRecord(ViewId);
                break;
            case 4:
                readAllRecord();
                break;
            case 5:
                ArrayList idList = new ArrayList();
                System.out.println("Enter i: ");
                input = s.nextInt();
                while (input>0){
                    System.out.println("Enter id: ");
                    int id = s.nextInt();
                    idList.add(id);
                }
                readAllMatchingRecord(idList);
        }
    }

    public static void inputUser(student stu, Scanner s){
        int Id;
        String Lastname, Firstname, Classes;

        System.out.print("Enter ID: ");
        Id = s.nextInt();
        stu.setID(Id);

        System.out.print("Enter lastname: ");
        Lastname = s.next();
        stu.setLastname(Lastname);

        System.out.print("Enter firstname: ");
        Firstname = s.next();
        stu.setFirstname(Firstname);

        System.out.print("Enter class: ");
        Classes = s.next();
        stu.setClass(Classes);
    }

    public static void insertRecord(student dto){
        try{
            st.execute("insert into student(id,lastname,firstname,class) values ('"+dto.getI()+"', '"+dto.getLname()+"', '"+dto.getFname()+"', '"+dto.getC()+"')");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void insertRecordCollection(ArrayList<student> dtoList){
        for(student dtoElement: dtoList){
            try{
                st.execute("insert into student(id,lastname,firstname,class) values ('"+dtoElement.getI()+"', '"+dtoElement.getLname()+"', '"+dtoElement.getFname()+"', '"+dtoElement.getC()+"')");
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void readRecord(int id){
        try {
            ResultSet rs = st.executeQuery("select * from student where id = '"+id+"'");
            while(rs.next()){
                String Id = rs.getString("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String classes = rs.getString("class");
                System.out.println(Id + ":" + lastname +":"+firstname + ":" + classes);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void readAllRecord(){
        try {
            ResultSet rs = st.executeQuery("select * from student");
            while(rs.next()){
                String Id = rs.getString("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String classes = rs.getString("class");
                System.out.println(Id + ":" + lastname +":"+firstname + ":" + classes);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static ArrayList readAllMatchingRecord(ArrayList<Integer> list){
        ArrayList<student> tempList = new ArrayList<>();
        student stu = new student();
        for(int i:list){
            try {
                ResultSet rs = st.executeQuery("select * from student where id = '"+list.get(i)+"'");
                while(rs.next()){
                    stu.setID(rs.getInt("id"));
                    stu.setLastname(rs.getString("lastname"));
                    stu.setFirstname(rs.getString("firstname"));
                    stu.setClass(rs.getString("class"));
                    tempList.add(stu);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return tempList;
    }
}

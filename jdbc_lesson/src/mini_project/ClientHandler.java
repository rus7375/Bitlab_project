package mini_project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler extends  Thread{
    private Socket socket;
    ArrayList<Student> students;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            DBManager db = new DBManager();
            db.connectToDB();

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            PackageData data = null;

            while((data = (PackageData) inputStream.readObject())!=null) {
//          Закинуть в бд в зависимости от команды
                System.out.println(data.getOperationType() + ", " + data.getStudent());
                if(data.getOperationType().equalsIgnoreCase("add_student")){
                    saveStudent(data.getStudent());
                }else if (data.getOperationType().equalsIgnoreCase("list_students")) {
                    students = new ArrayList<>();
                    students = getStudents();
                    outputStream.writeObject(students);
                    System.out.println(students);
                }

            }

            inputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Client has been disconnected");
            }


    }

    public static void saveStudent(Student student){
        try {
            PreparedStatement st = DBManager.getConnection().
                    prepareStatement("insert into students(name, surname, age) values(?,?,?)");
            st.setString(1,student.getName());
            st.setString(2,student.getSurname());
            st.setInt(3,student.getAge());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            PreparedStatement st = DBManager.getConnection().prepareStatement("select * from students");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");

                students.add(new Student(id,name,surname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}

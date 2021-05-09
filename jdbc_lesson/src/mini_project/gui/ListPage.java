package mini_project.gui;

import mini_project.Client;
import mini_project.PackageData;
import mini_project.ServerHandler;
import mini_project.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ListPage extends JPanel {
    private MainFrame parent;
    ArrayList<Student> students = null;

    private JLabel label;
    private JButton back;

    private static String[] header = {"Id", "Name", "Surname", "age"};
    private static JTable table;
    private JScrollPane scrollPane;

    public ListPage(MainFrame parent){
        this.parent = parent;

//        try {
//            Client.ServerConnection.getServerHandler()
//                    .getOutputStream().writeObject(new PackageData("list_students", null));
//            students = (ArrayList<Student>) Client.ServerConnection.getServerHandler().getInputStream().readObject();
//            System.out.println(students);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        setSize(500,400);
        setLayout(null);


        label = new JLabel("Students list");
        label.setSize(300,20);
        label.setLocation(200,40);
        add(label);

        back = new JButton("BACK");
        back.setSize(100,35);
        back.setLocation(180,300);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getListPage().setVisible(false);
                parent.getMenu().setVisible(true);
            }
        });
        add(back);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN,12));
        table.setRowHeight(30);
        table.setEnabled(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(350,200);
        scrollPane.setLocation(70,75);
        add(scrollPane);

    }

    public static void  generateTable(ArrayList<Student> students) {
        Object data[][] =  new Object[students.size()][4];

        for (int i = 0; i < students.size() ; i++) {
            if(students.get(i) != null){
                data[i][0] = students.get(i).getId();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getSurname();
                data[i][3] = students.get(i).getAge();

            }
        }

        DefaultTableModel model = new DefaultTableModel(data,header);
        table.setModel(model);
        System.out.println(students);
    }


}

package mini_project.gui;

import mini_project.Client;
import mini_project.PackageData;
import mini_project.ServerHandler;
import mini_project.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddPage extends JPanel {
//    private ServerHandler serverHandler;

    private MainFrame parent;

    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel ageLabel;

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField ageField;

    private JButton addButton;
    private JButton backButton;

    public AddPage(MainFrame parent) {
        this.parent = parent;

        setSize(500,400);
        setLayout(null);

        nameLabel = new JLabel("NAME: ");
        nameLabel.setSize(80,30);
        nameLabel.setLocation(80,80);
        add(nameLabel);

        surnameLabel = new JLabel("SURNAME: ");
        surnameLabel.setSize(80,30);
        surnameLabel.setLocation(80,130);
        add(surnameLabel);

        ageLabel = new JLabel("AGE: ");
        ageLabel.setSize(80,30);
        ageLabel.setLocation(80,180);
        add(ageLabel);

        nameField = new JTextField();
        nameField.setSize(200,30);
        nameField.setLocation(180, 80);
        add(nameField);

        surnameField = new JTextField();
        surnameField.setSize(200,30);
        surnameField.setLocation(180, 130);
        add(surnameField);


        ageField = new JTextField();
        ageField.setSize(200,30);
        ageField.setLocation(180, 180);
        add(ageField);

        addButton = new JButton("ADD");
        addButton.setSize(120,35);
        addButton.setLocation(90,260);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                int age = Integer.parseInt(ageField.getText());

                try {
                    Client.ServerConnection.getServerHandler().getOutputStream().writeObject(new PackageData(
                            "add_student", new Student(null, name, surname, age)));


                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                nameField.setText("");
                surnameField.setText("");
                ageField.setText("");



            }
        });
        add(addButton);

        backButton = new JButton("BACK");
        backButton.setSize(120,35);
        backButton.setLocation(280,260);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getAddPage().setVisible(false);
                parent.getMenu().setVisible(true);
            }
        });
        add(backButton);
    }

}

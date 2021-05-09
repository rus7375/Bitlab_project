package mini_project.gui;

import mini_project.Client;
import mini_project.PackageData;
import mini_project.ServerHandler;
import mini_project.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends JPanel {
//    ServerHandler serverHandler;
    MainFrame parent;

    JButton addButton;
    JButton listButton;
    JButton exitButton;

    public MainMenu(MainFrame parent) {
        this.parent = parent;

        setSize(500,400);
        setLayout(null);

        addButton =  new JButton("ADD STUDENT");
        addButton.setSize(170,35);
        addButton.setLocation(155,100);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMenu().setVisible(false);
                parent.getAddPage().setVisible(true);
            }
        });
        add(addButton);

        listButton = new JButton("LIST STUDENTS");
        listButton.setSize(170,35);
        listButton.setLocation(155,150);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                   Client.ServerConnection.getServerHandler().getOutputStream().writeObject(new PackageData("list_students", null));
//toDo
                    ListPage.generateTable((ArrayList<Student>) Client
                            .ServerConnection.getServerHandler()
                            .getInputStream().readObject());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                parent.getMenu().setVisible(false);
                parent.getListPage().setVisible(true);
            }
        });
        add(listButton);

        exitButton = new JButton("EXIT");
        exitButton.setSize(170,35);
        exitButton.setLocation(155,200);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
    }

}

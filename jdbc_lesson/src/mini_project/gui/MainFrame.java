package mini_project.gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    MainMenu menu;
    AddPage addPage;
    ListPage listPage;

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("STUDENT APPLICATION");
        setSize(500,400);
        setLayout(null);

        menu = new MainMenu(this);
        menu.setVisible(true);
        add(menu);

        addPage = new AddPage(this);
        addPage.setVisible(false);
        add(addPage);

        listPage = new ListPage(this);
        listPage.setVisible(false);
        add(listPage);

    }

    public MainMenu getMenu() {
        return menu;
    }

    public AddPage getAddPage() {
        return addPage;
    }

    public ListPage getListPage() {
        return listPage;
    }
}

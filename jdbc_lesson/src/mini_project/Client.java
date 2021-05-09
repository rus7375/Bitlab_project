package mini_project;

import mini_project.gui.MainFrame;

public class Client {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public static class ServerConnection{
        private static ServerHandler serverHandler = new ServerHandler();

        public static ServerHandler getServerHandler() {
            return serverHandler;
        }
    }
}

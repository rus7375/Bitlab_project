package mini_project;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(2020);

            while(true) {
                Socket socket = server.accept();
                System.out.println("Client connected");
                ClientHandler ch = new ClientHandler(socket);
                ch.start();

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

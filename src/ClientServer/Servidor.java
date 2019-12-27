package ClientServer;

import MediaCenter.MediaCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    ServerSocket sSock;
    ArrayList<Thread> utilizadores;
    MediaCenter mediaCenter;

    public Servidor() {
        try {
            sSock = new ServerSocket(12345);
        } catch(IOException e){
            e.printStackTrace();
        }
        utilizadores = new ArrayList<>();
        mediaCenter = new MediaCenter();
    }

    public void serverStart() throws IOException{
        Socket clSocket = new Socket();
        Thread newThread;
        while(clSocket != null){
            clSocket = sSock.accept();
            newThread = new Thread(new userThread(clSocket, mediaCenter));
            utilizadores.add(newThread);
            newThread.start();
        }
    }

}
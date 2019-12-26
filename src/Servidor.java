import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    ServerSocket sSock;
    ArrayList<Thread> clientes;
    BancoServidor banco;

    public Servidor() {
        try {
            sSock = new ServerSocket(12345);
        } catch(IOException e){
            e.printStackTrace();
        }
        clientes = new ArrayList<>();
        banco = new BancoServidor(0);
    }

    public void serverStart() throws IOException{
        Socket clSocket = new Socket();
        Thread newThread;
        while(clSocket != null){
            clSocket = sSock.accept();
            newThread = new Thread(new clientThread(clSocket, banco));
            clientes.add(newThread);
            newThread.start();
        }
    }

}
package ClientServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Servidor server = new Servidor();
        try{
            server.serverStart();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

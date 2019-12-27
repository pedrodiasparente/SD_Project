package ClientServer;

import Exceptions.DadosInexistentesException;
import Exceptions.DadosJaExistemException;
import MediaCenter.MediaCenter;
import MediaCenter.MediaCenterRemoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class userThread implements Runnable{
    private Socket clSocket;
    private MediaCenter mediaCenter;

    userThread(Socket socket, MediaCenter mediaCenter){
        this.clSocket = socket;
        this.mediaCenter = mediaCenter;
    }

    @Override
    public void run() {
        String info = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clSocket.getOutputStream());

            while (clSocket.isConnected() && !info.equals("quit")) {

                info = in.readLine();
                String[] dataExe = info.split(" ");

                System.out.println(info + " [Executando...]");

                switch (dataExe[0]){
                    case "registo" :
                        try{
                            mediaCenter.registo(dataExe[1], dataExe[2]);
                        } catch (DadosJaExistemException e){
                            out.println("Erro");
                        }
                        out.println("Sucesso");
                        break;

                    case "login" :
                        try {
                            mediaCenter.login(dataExe[1], dataExe[2]);
                        } catch(DadosInexistentesException e){
                            out.println("Erro");
                        }
                        out.println("Sucesso");
                        break;

                    default :
                        out.println("Op invalida");
                        break;
                }
                out.flush();
            }
            clSocket.shutdownOutput();
            clSocket.shutdownInput();
            clSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
